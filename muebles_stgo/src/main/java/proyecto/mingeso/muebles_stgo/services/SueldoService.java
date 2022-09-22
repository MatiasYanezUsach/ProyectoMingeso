package proyecto.mingeso.muebles_stgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.mingeso.muebles_stgo.entities.*;
import proyecto.mingeso.muebles_stgo.repositories.JustificativoRepository;
import proyecto.mingeso.muebles_stgo.repositories.RelojRepository;
import proyecto.mingeso.muebles_stgo.repositories.SolicitudRepository;
import proyecto.mingeso.muebles_stgo.repositories.SueldoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class SueldoService {
    @Autowired
    SueldoRepository sueldoRepository;
    RelojRepository relojRepository;
    SolicitudRepository solicitudRepository;
    JustificativoRepository justificativoRepository;

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
        LocalDateTime horaSalida = LocalDateTime.parse("18:00");
        ArrayList<SolicitudEntity> registroSolicitudes = solicitudRepository.findByRut(empleado.getRut());
        ArrayList<RelojEntity> registroHoras = relojRepository.findByRut(empleado.getRut());
        if (Objects.equals(empleado.getCategoria(), "A")){
            categoria = 25000;
        }
        if (Objects.equals(empleado.getCategoria(), "B")){
            categoria = 20000;
        }
        if (Objects.equals(empleado.getCategoria(), "C")){
            categoria = 10000;
        }
        for(int i = 0; registroSolicitudes.get(i) != null; i++){
            for(int j = 0; registroHoras.get(j) != null; j++){
                if(registroSolicitudes.get(i).getFecha_cubridora() == (registroHoras.get(j).getFecha())){
                    horasExtras = registroHoras.get(j).getHora().getHour() - horaSalida.getHour();
                    if(horasExtras > 1){
                        montoPorHora = montoPorHora + categoria*horasExtras;
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
       ArrayList<JustificativoEntity> registroJustificados = justificativoRepository.findByRut(empleado.getRut());
       ArrayList<RelojEntity> registroHoras = relojRepository.findByRut(empleado.getRut());
       for(int i = 0; registroHoras.get(i) != null; i++) {
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
       }
       for(int i = 0; registroJustificados.get(i) != null; i++) {
           for(int j = 0; registroHoras.get(j) != null; j++){
               if(registroJustificados.get(i).getFecha_cubridora() == registroHoras.get(j).getFecha()){
                   montoPorAtraso = montoPorAtraso - sueldoMensual * 0.15;
               }
           }
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
    public SueldoEntity guardarSueldo(SueldoEntity sueldo){
        return sueldoRepository.save(sueldo);
    }
    public SueldoEntity crearSueldo (SueldoEntity sueldo, EmpleadoEntity empleado){
        SueldoEntity nuevoSueldo = sueldoRepository.save(new SueldoEntity(sueldo.getId_sueldo(),empleado.getRut(),nombreCompletoEmpleado(empleado),empleado.getCategoria(),calcularAniosServicio(empleado),calcularSueldoFijoMensual(empleado),montoBonificacionAniosServicio(empleado),montoPagoHorasExtras(empleado),montoDescuentosAtrasos(empleado),calcularSueldoBruto(empleado),calcularCotizacionPrevisional(empleado),calcularCotizacionPlanSalud(empleado),calcularSueldoFinal(empleado)));
        return guardarSueldo(nuevoSueldo);
    }

}