PGDMP         9                |            patikahotel    13.13    13.13 $    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16448    patikahotel    DATABASE     i   CREATE DATABASE patikahotel WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE patikahotel;
                postgres    false            �            1259    16490    book    TABLE     f  CREATE TABLE public.book (
    id integer NOT NULL,
    room_id integer NOT NULL,
    name text NOT NULL,
    mail text NOT NULL,
    mpno text NOT NULL,
    tcno text NOT NULL,
    note text,
    pension text NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL,
    price double precision NOT NULL,
    adult integer,
    child integer
);
    DROP TABLE public.book;
       public         heap    postgres    false            �            1259    16503    book_id_seq    SEQUENCE     �   ALTER TABLE public.book ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    208            �            1259    16461    hotel    TABLE     8  CREATE TABLE public.hotel (
    id integer NOT NULL,
    name text NOT NULL,
    mail text NOT NULL,
    mpno text NOT NULL,
    star text,
    adress text NOT NULL,
    parking boolean,
    wifi boolean,
    pool boolean,
    fitness boolean,
    concierge boolean,
    spa boolean,
    room_service boolean
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16459    hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    203            �            1259    16471    pension    TABLE       CREATE TABLE public.pension (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    ultra_all_inc boolean,
    all_inc boolean,
    bed_breakfast boolean,
    full_board boolean,
    half_board boolean,
    room_only boolean,
    full_board_e_alcohol boolean
);
    DROP TABLE public.pension;
       public         heap    postgres    false            �            1259    16469    pension_id_seq    SEQUENCE     �   ALTER TABLE public.pension ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    205            �            1259    16485    room    TABLE     �  CREATE TABLE public.room (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    season_id integer,
    room_type text NOT NULL,
    stock integer NOT NULL,
    adult_price double precision NOT NULL,
    child_price double precision NOT NULL,
    bed integer NOT NULL,
    square_meter double precision NOT NULL,
    television boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    cash_case boolean NOT NULL,
    projection boolean NOT NULL,
    pension text
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    16483    room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    207            �            1259    16523    season    TABLE     �   CREATE TABLE public.season (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    16521    season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    211            �            1259    16451    user    TABLE     �   CREATE TABLE public."user" (
    id integer NOT NULL,
    username text NOT NULL,
    pass text NOT NULL,
    role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16449    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    201            �          0    16490    book 
   TABLE DATA           �   COPY public.book (id, room_id, name, mail, mpno, tcno, note, pension, start_date, finish_date, price, adult, child) FROM stdin;
    public          postgres    false    208   �)       �          0    16461    hotel 
   TABLE DATA              COPY public.hotel (id, name, mail, mpno, star, adress, parking, wifi, pool, fitness, concierge, spa, room_service) FROM stdin;
    public          postgres    false    203   �*       �          0    16471    pension 
   TABLE DATA           �   COPY public.pension (id, hotel_id, ultra_all_inc, all_inc, bed_breakfast, full_board, half_board, room_only, full_board_e_alcohol) FROM stdin;
    public          postgres    false    205   �+       �          0    16485    room 
   TABLE DATA           �   COPY public.room (id, hotel_id, season_id, room_type, stock, adult_price, child_price, bed, square_meter, television, minibar, game_console, cash_case, projection, pension) FROM stdin;
    public          postgres    false    207   j,       �          0    16523    season 
   TABLE DATA           G   COPY public.season (id, hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    211   �-       �          0    16451    user 
   TABLE DATA           :   COPY public."user" (id, username, pass, role) FROM stdin;
    public          postgres    false    201   .       �           0    0    book_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.book_id_seq', 27, true);
          public          postgres    false    209            �           0    0    hotel_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.hotel_id_seq', 42, true);
          public          postgres    false    202            �           0    0    pension_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.pension_id_seq', 14, true);
          public          postgres    false    204            �           0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 29, true);
          public          postgres    false    206            �           0    0    season_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.season_id_seq', 19, true);
          public          postgres    false    210            �           0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 20, true);
          public          postgres    false    200            M           2606    16512    book book_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.book DROP CONSTRAINT book_pkey;
       public            postgres    false    208            G           2606    16468    hotel hotel_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    203            I           2606    16475    pension pension_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    205            K           2606    16489    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    207            O           2606    16527    season season_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    211            E           2606    16458    user user_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    201            Q           2606    16528    season fkey    FK CONSTRAINT     u   ALTER TABLE ONLY public.season
    ADD CONSTRAINT fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) NOT VALID;
 5   ALTER TABLE ONLY public.season DROP CONSTRAINT fkey;
       public          postgres    false    211    203    2887            P           2606    16516    room hotel_fkey    FK CONSTRAINT     y   ALTER TABLE ONLY public.room
    ADD CONSTRAINT hotel_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) NOT VALID;
 9   ALTER TABLE ONLY public.room DROP CONSTRAINT hotel_fkey;
       public          postgres    false    2887    203    207            �   �   x���A
�0E��S� ��L벂� x7Q
�6t�ĥFz/�E~��ޟab��^�b���6bY�N�*Tc�� 3D�1�n��:1\+/V�l`�<E
3a�AD ��%0��z��/�6�ؤ��ռxk�nW�=��E���N�ec���W�Ɣ.�\���C,S^�Pb���E��?ן��$I�	[�      �   F  x�U��n�0���S�	��q���VB����LAvb�8������[x����������N`�V��:e ��켎ֶ�8��0 �es)>����"�D���?�&�s� 2p�VsߘH�y�*��ъԤRf]k�RS!3�T����޸��o�R8`��c��$"� ��S��6���/$,�˺��� "���h<��_��I�#�빵�,��J�نr��fuQ�ݶ{����l��b��tH;�Ⱥ$&cZ�U�p���>G�YO��6����u�����"x��1���	)�D�4����#�<nH�$#���%e�SFO��\E��?Ń��      �   f   x�]�K� E���b��Ǭ��G@Al: ��K��kL�C����5�F�z5;����	�W�2$�J�
$��A�J=1fH���<�Y([S�7vDt��7g      �   V  x���MN�@���)8�鿢ak�(����q ��j�/�m���L�0q,��꽂P8��=>�< ��c $��fx�.����]�ϯsA-(�y8��+Q ��HO�'`?�����Nc�H\,jr���2��*���ܡ���g�g�/"n6�+��E[�&���3�d�5��C�F^�]�!tJ�n<u �Qtb:�y���l��H:A���S���O�t�E�\g��*蘄*R=P��7c��A{��A/�l��7��]8�(�88ѐ��eԶI��5��ðzA0��dbn퍨�.�G�zH��S��#��t(כ���m���������%S���B��*T�y      �   �   x�u�K�0D�p�T>vr������6�X��` ����B��`IR�@Q��De �Itt���u�T�ϧ��쩕۽�9ӯ'��D/������ޘk�!��0��fEi��]s�����_�o�/iOypF#���I����y�q�,}������`�XS      �   v   x�3�LL����442�tt����24q�$s����G��rY@���3�L�-�ɯ�,�L+JE�54I�U"�̀d"�6�hF6�(d�i
�A��i0�4D�id 2
A���qqq �c2�     