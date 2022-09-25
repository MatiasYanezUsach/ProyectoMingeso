package proyecto.mingeso.muebles_stgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.mingeso.muebles_stgo.entities.*;
import proyecto.mingeso.muebles_stgo.repositories.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Service
public class SueldoService {
    @Autowired
    SueldoRepository sueldoRepository;
    @Autowired
    RelojRepository relojRepository;
    @Autowired
    SolicitudRepository solicitudRepository;
    @Autowired
    JustificativoRepository justificativoRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;

    int findeMes = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
    int mesActual = Calendar.getInstance().get(Calendar.MONTH);
    int anioActual = Calendar.getInstance().get(Calendar.YEAR);
    Date inicioMes = new Date(anioActual,mesActual, 1);
    Date finMes = new Date(anioActual,mesActual, findeMes);
    static long diasHabiles(Date inicioMes, Date finalMes){
        Calendar fechaActual = Calendar.getInstance();
        fechaActual.setTime(inicioMes);
        int diasSemana = fechaActual.get(Calendar.DAY_OF_WEEK);
        fechaActual.add(Calendar.DAY_OF_WEEK, -diasSemana);
        Calendar fechaActual2 = Calendar.getInstance();
        fechaActual2.setTime(finalMes);
        int diasSemana2 = fechaActual2.get(Calendar.DAY_OF_WEEK);
        fechaActual2.add(Calendar.DAY_OF_WEEK, -diasSemana2);
        long dias = (fechaActual2.getTimeInMillis()-fechaActual.getTimeInMillis())/(1000*60*60*24);
        long diasTrabajables = dias-(dias*2/7);
        if (diasSemana == Calendar.SUNDAY) {
            diasSemana = Calendar.MONDAY;
        }
        if (diasSemana2 == Calendar.SUNDAY) {
            diasSemana2 = Calendar.MONDAY;
        }
        return diasTrabajables-diasSemana+diasSemana2+1;
    }


    public String nombreCompletoEmpleado (EmpleadoEntity empleado){
        String apellidos = empleado.getApellidos();
        String nombre = empleado.getNombres();
        return apellidos+" "+nombre;
    }

    public double calcularSueldoFijoMensual (EmpleadoEntity empleado){
        double sueldoMensual = 0;
        if (Objects.equals(empleado.getCategoria(), "A")){
            sueldoMensual = 1700000;
        }
        if (Objects.equals(empleado.getCategoria(), "B")){
            sueldoMensual = 1200000;
        }
        if (Objects.equals(empleado.getCategoria(), "C")){
            sueldoMensual = 800.000;
        }
        return sueldoMensual;
    }
    public double montoPagoHorasExtras(EmpleadoEntity empleado){
        double montoPorHora = 0;
        double categoria = 0;
        int horasExtras;
        int cantidadSolicitudes = 0;
        int cantidadMarcas = 0;
        LocalTime horaSalida = LocalTime.parse("18:00");
        ArrayList<SolicitudEntity> registroSolicitudes = solicitudRepository.findByRut(empleado.getRut());
        ArrayList<RelojEntity> registroHoras = relojRepository.findByRut(empleado.getRut());
        for(SolicitudEntity solicitudes : registroSolicitudes) {
            cantidadSolicitudes++;
        }
        for(RelojEntity marcas : registroHoras) {
            cantidadMarcas++;
        }

        if (cantidadSolicitudes <= 0) {
            return montoPorHora;
        }
        if (Objects.equals(empleado.getCategoria(), "A")){
            categoria = 25000;
        }
        if (Objects.equals(empleado.getCategoria(), "B")) {
            categoria = 20000;
        }
        if (Objects.equals(empleado.getCategoria(), "C")){
        categoria = 10000;
        }
        for (int i = 0; i < cantidadSolicitudes; i++) {
            System.out.println("a"+ registroSolicitudes.get(i).getFecha_cubridora());
            for (int j = 0; j < cantidadMarcas; j++) {
                System.out.println("b"+ registroHoras.get(j).getFecha());
                if (registroSolicitudes.get(i).getFecha_cubridora().getDayOfMonth() == registroHoras.get(j).getFecha().getDayOfMonth()) {
                    horasExtras = registroHoras.get(j).getHora().getHour() - horaSalida.getHour();
                    if (horasExtras >= 1) {
                        montoPorHora = montoPorHora + (categoria * horasExtras);
                    }
                }
            }
        }
        return montoPorHora;
    }
    public int calcularAniosServicio (EmpleadoEntity empleado) {
        int anioActual = LocalDate.now().getYear();
        return anioActual - empleado.getFecha_in().getYear();
    }

    public double montoBonificacionAniosServicio(EmpleadoEntity empleado){
        int aniosServicio = calcularAniosServicio(empleado);
        double sueldoMensual = calcularSueldoFijoMensual (empleado);
        double montoBonificacion = 0;
        if (aniosServicio < 5){
            return montoBonificacion;
        }
        if(aniosServicio < 10){
            montoBonificacion = sueldoMensual*0.5;
        }
        if(aniosServicio >= 10 && aniosServicio < 15){
            montoBonificacion = sueldoMensual*0.8;
        }
        if(aniosServicio >= 15 && aniosServicio < 20){
            montoBonificacion = sueldoMensual*0.11;
        }
        if(aniosServicio >= 20 && aniosServicio < 25){
            montoBonificacion = sueldoMensual*0.14;
        }
        if(aniosServicio >= 25){
            montoBonificacion = sueldoMensual*0.17;
        }
        return montoBonificacion;
    }
   public double montoDescuentosAtrasos(EmpleadoEntity empleado) {
       double montoPorAtraso = 0;
       double sueldoMensual = calcularSueldoFijoMensual (empleado);
       int cantidadJustificativos = 0;
       int reservaJustivicativos = 0;
       int cantidadMarcas = 0;
       double diasHabiles = ((double) diasHabiles(inicioMes,finMes)) * 2;
       ArrayList<JustificativoEntity> registroJustificados = justificativoRepository.findByRut(empleado.getRut());
       ArrayList<RelojEntity> registroHoras = relojRepository.findByRut(empleado.getRut());
       for(JustificativoEntity justificativos : registroJustificados) {
           cantidadJustificativos++;
       }
       for(RelojEntity marcas : registroHoras) {
           cantidadMarcas++;
       }
       reservaJustivicativos = cantidadJustificativos;
       for(int i = 0; i < cantidadMarcas; i++) {
           if (registroHoras.get(i).getHora().getHour() == 8){
               if (registroHoras.get(i).getHora().getMinute() > 10 && registroHoras.get(i).getHora().getMinute() <= 25){
                   montoPorAtraso = montoPorAtraso + sueldoMensual*0.01;
               }
               if (registroHoras.get(i).getHora().getMinute() > 25 && registroHoras.get(i).getHora().getMinute() <= 45){
                   montoPorAtraso = montoPorAtraso + sueldoMensual*0.03;
               }
               if (registroHoras.get(i).getHora().getMinute() > 45){
                   montoPorAtraso = montoPorAtraso + sueldoMensual*0.06;
               }
           }
           else if (registroHoras.get(i).getHora().getHour() == 9){
               if (registroHoras.get(i).getHora().getMinute() > 0 && registroHoras.get(i).getHora().getMinute() <= 10) {
                   montoPorAtraso = montoPorAtraso + sueldoMensual * 0.06;
               }
               if (registroHoras.get(i).getHora().getMinute() > 10) {
                   montoPorAtraso = montoPorAtraso + sueldoMensual * 0.15;
               }
           }
           else{
               if (registroHoras.get(i).getHora().getHour() > 9 && registroHoras.get(i).getHora().getHour() < 18){
                   montoPorAtraso = montoPorAtraso + sueldoMensual * 0.15;
               }
           }
           diasHabiles = diasHabiles - 1;
       }
       diasHabiles = Math.ceil(diasHabiles/2);
       for(int i = 0; i < cantidadJustificativos; i++) {
           for(int j = 0; j < cantidadMarcas; j++){
               if(registroJustificados.get(i).getFecha_cubridora() == registroHoras.get(j).getFecha()){
                   montoPorAtraso = montoPorAtraso - sueldoMensual * 0.15;
                   reservaJustivicativos = reservaJustivicativos -1;
               }
           }
       }
       if(diasHabiles > 0 && reservaJustivicativos == 0){
           montoPorAtraso = montoPorAtraso + (sueldoMensual * 0.15)*diasHabiles;
       }
       if(diasHabiles > reservaJustivicativos){
           montoPorAtraso = montoPorAtraso + (sueldoMensual * 0.15)*(diasHabiles - reservaJustivicativos);
       }
       return montoPorAtraso;
   }
   public double calcularSueldoBruto(EmpleadoEntity empleado){
        double descuentos = montoDescuentosAtrasos(empleado);
        double bonificaciones = montoBonificacionAniosServicio(empleado);
        double montoHoraExtra = montoPagoHorasExtras(empleado);
        double sueldoFijo = calcularSueldoFijoMensual(empleado);
        return bonificaciones + montoHoraExtra + (sueldoFijo - descuentos);
    }
    public double calcularCotizacionPrevisional(EmpleadoEntity empleado){
        double sueldoBruto = calcularSueldoBruto(empleado);
        return sueldoBruto * 0.1;
    }
    public double calcularCotizacionPlanSalud(EmpleadoEntity empleado){
        double sueldoBruto = calcularSueldoBruto(empleado);
        return sueldoBruto * 0.08;
    }
    public double calcularSueldoFinal(EmpleadoEntity empleado){
        double sueldoBruto = calcularSueldoBruto(empleado);
        double cotizacionPlanSalud = calcularCotizacionPlanSalud(empleado);
        double cotizacionPrevisional = calcularCotizacionPrevisional(empleado);
        return (sueldoBruto-cotizacionPrevisional)-cotizacionPlanSalud;
    }
    public void crearSueldoEmpleado (SueldoEntity sueldo, EmpleadoEntity empleado){
        sueldoRepository.save(new SueldoEntity(sueldo.getId_sueldo(), empleado.getRut(), nombreCompletoEmpleado(empleado), empleado.getCategoria(), calcularAniosServicio(empleado), calcularSueldoFijoMensual(empleado), montoBonificacionAniosServicio(empleado), montoPagoHorasExtras(empleado), montoDescuentosAtrasos(empleado), calcularSueldoBruto(empleado), calcularCotizacionPrevisional(empleado), calcularCotizacionPlanSalud(empleado), calcularSueldoFinal(empleado)));
    }
    public void sueldosGenerales (ArrayList<EmpleadoEntity> empleados){
        int cantidadEmpleados = 0;
        for(EmpleadoEntity cantEmpleados : empleados) {
            cantidadEmpleados++;
        }
        SueldoEntity nuevoSueldo = sueldoRepository.save(new SueldoEntity(1L, empleados.get(0).getRut(),nombreCompletoEmpleado(empleados.get(0)),empleados.get(0).getCategoria(),calcularAniosServicio(empleados.get(0)),calcularSueldoFijoMensual(empleados.get(0)),montoBonificacionAniosServicio(empleados.get(0)),montoPagoHorasExtras(empleados.get(0)),montoDescuentosAtrasos(empleados.get(0)),calcularSueldoBruto(empleados.get(0)),calcularCotizacionPrevisional(empleados.get(0)),calcularCotizacionPlanSalud(empleados.get(0)),calcularSueldoFinal(empleados.get(0))));
        for(int i = 1; i < cantidadEmpleados; i++){
            crearSueldoEmpleado(nuevoSueldo, empleados.get(i));
        }
    }
    public ArrayList<SueldoEntity> obtenerPlanilla(){
        return (ArrayList<SueldoEntity>) sueldoRepository.findAll();
    }
}