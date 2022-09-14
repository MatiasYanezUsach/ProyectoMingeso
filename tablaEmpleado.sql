PGDMP         :                z           mingeso    14.5    14.5     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    25928    mingeso    DATABASE     c   CREATE DATABASE mingeso WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE mingeso;
                postgres    false            �            1259    25942    empleado    TABLE       CREATE TABLE public.empleado (
    id integer NOT NULL,
    rut character varying(100),
    apellidos character varying(100),
    nombres character varying(100),
    fecha_nac character varying(100),
    categoria integer,
    fecha_in character varying(100)
);
    DROP TABLE public.empleado;
       public         heap    postgres    false            �            1259    25941    empleado_id_seq    SEQUENCE     �   CREATE SEQUENCE public.empleado_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.empleado_id_seq;
       public          postgres    false    210            �           0    0    empleado_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.empleado_id_seq OWNED BY public.empleado.id;
          public          postgres    false    209            [           2604    25945    empleado id    DEFAULT     j   ALTER TABLE ONLY public.empleado ALTER COLUMN id SET DEFAULT nextval('public.empleado_id_seq'::regclass);
 :   ALTER TABLE public.empleado ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209    210            �          0    25942    empleado 
   TABLE DATA           _   COPY public.empleado (id, rut, apellidos, nombres, fecha_nac, categoria, fecha_in) FROM stdin;
    public          postgres    false    210          �           0    0    empleado_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.empleado_id_seq', 100, true);
          public          postgres    false    209            ]           2606    25949    empleado empleado_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.empleado DROP CONSTRAINT empleado_pkey;
       public            postgres    false    210            �   �
  x�UX�r�H<_��=i���ђ<��%�B�zb&��"�dA@��P_�Y� H߬0�U����ռ�R0/��r��o���}��1��B�b%c+fV\W�b~�d-*���B�+^=�~C;V�aL�Xq�}�r9��s+�k�|�0����~��m�mw!���Є6 ��G>^�,��еB2g�0��W��I���� �!	_	Y�)~@�(�iŭ����_CS}J�-���t���MŭP�(v%����s�'*J��]q�䐄�%�3�_��S�mPW�حwtzEUqS��3qWƕ^\��Kl�⯰���*Ԓ e����p��ǩ?��7p������Q0�b��p�.F:��&�׻8F �
7�)�S�0��XB�rŽr�_)|<6�ǏX=��&R:/Z!J�>�@�4 }w��s� �k�#8�Њ�栀�+���u��쪧�D'3�ʳD7Q�G_sE$�|�3"G�[w<���*�D���n�W���Oo�>���z���dD7�@6�˅A[|����Ә��r��	�r�8�% ���+[=�vO�=vú�XL�-�SҚ�C*�
7����4��k	RD;�Sq��0m���j�v�/�:��qc��[�`� 7�����م㦏c���4�K�����4�[#-�=0��)ӓJd~���pH��}��7E���ЈrǹO��P6S�hb��4eb�G"`�%�]SZ��)rBl��P�Ε����x:$d�}��(��܁����h��n�0� z��N�6�cV Atbj�;dv�R[#�f�t{�<�i���װ;J��|W��J�Fpn4�)\�z�<�qX
�%��$d�Q�K�Q`�k"��&K�\িk��)f�	Áb~m�}:L��z�~*B�j	.(�I�q���x������,�n�҂Dh[�B���
�>n�Ը��d.�,b��Z������c��	��>L4����PH�jI:a4��|I7����X}K��ÔϭP�8�=�2��J�o�� @����6S��Eu	ZX��b�@]��#n7�af�ɥ��Z�8�fB���0,�H��A�Ӓ�!�m�8Uɜ$֢��H�Bj������L���a
1�æ=�O�U��Ii�����p�9Z�,���uz~>�2�h$��4�l��8x	Z��i���n�C��e�+�L�ƚ5��@�"W�,4:�]��vM1��rm��i��S��،]+��f���I� AE%�q�bOo�Ƀ�e����C��p��	f����P컷ynB?��/W�`��$��k�s[5̀��y���'I�&��8��2$���-�tM̘��7>�6`��W�hd44��%�1o?A�Ki:��[��\�`8���{���b�<��qk����C�>J��{�i#�Q�(Wz�,�Ei�c�q*�)]� �M����(SzC�(_k��8�5����v�<6�&{���R,����)�U���&:`�c���F��G�LhbJ��;��ؑ=¹�����b��VT����nP\����fe�|)������["�;��a&�Ɇ��$��}�	�P�Gr��:���F|��͚y�]<1B���૬�z?`/y��b��s#߭#<�e@4�n���l{Ao�]ۦ�z���eA��(�c�لX1�������!�i��d�[�nQ`��l�m��\^J:�H#�fLH`q{|��G�4�`��`��MAV�m����Nr�.��u��~Q>E��H��`�uԅ@.��>U_��5yLB�b&bT�B:��a���4H���XS�&g�J�b�ܞVBZ����y�27���EH�ڀ�C����ϩY��4rR�ϞOF���2�@4$���u�JJ'�M,K'Y2d4�!��M�ִ��h6����� Rg�.�w�[��|��4�D1;r�D�ڂkX�
��]<��=u�<E��� Hdi��Qiֻ��T��~���f4��Ջ��8�6���ы`.�6����&t]��l�&A��n��g����@ɖA�Ԓ�PV�`h��5z�5�'"��]����fdj���7V�I��7�Y2��i��[l&��ښ�V�/�Z��qh�K�L\T6�/������,�BxZ���,6�X����IjI�.]��-�L��"n�O�?:,�߱�e�����/@����U�<2�����NՇa��>��.���pB,R^B]�ib����I5���V4Ԋ��k4Rh	�>�0t�����ty�x��v�w"O�9��۾���&ù5�D^�a�E3Үw���w�XF��t
�쇄f�he�YI��4�����+n�>����ԲD�<v����T�<N��çO�i�r�o����
U��%���!�S�O���i"R-й\�	!7��]�_0E�ĉvO�2;=��ڑ@H�*�+[��O�׉�	o��ؠpGJJ�{EĲB�`3��Ai�R�A���yxڧ�7�n�!da�����jm�i󀠻l~�mB��> c�e��e�E���gz�u-� �����e�7�Y���T�"J/!O#"yE؞	>Gz4k',�",����fG?��O������I��L`��3���o����.��u��e��d-��� ܇�E�Nt��`W�� ��g}�#5���䱯�L�yF̞��X\� E68"HtM���Q�æ�[� �����;�0f �ax_������w)�����ozCb��z������c�Ӱ�`��Yc�cXS^��������V&��     