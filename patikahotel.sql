PGDMP                          |            patikahotel    13.13    13.13 )    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16448    patikahotel    DATABASE     i   CREATE DATABASE patikahotel WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE patikahotel;
                postgres    false            �            1259    16490    book    TABLE     @  CREATE TABLE public.book (
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
    price double precision NOT NULL
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
            public          postgres    false    205            �            1259    16495    reservation    TABLE     �   CREATE TABLE public.reservation (
    id integer NOT NULL,
    name text NOT NULL,
    mpno text,
    mail text,
    room_id integer NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    16493    reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    210            �            1259    16485    room    TABLE     �  CREATE TABLE public.room (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    pansion_id integer,
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
    projection boolean NOT NULL
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
    finish_date date NOT NULL,
    cost integer
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
            public          postgres    false    213            �            1259    16451    user    TABLE     �   CREATE TABLE public."user" (
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
   TABLE DATA           r   COPY public.book (id, room_id, name, mail, mpno, tcno, note, pension, start_date, finish_date, price) FROM stdin;
    public          postgres    false    208   J/       �          0    16461    hotel 
   TABLE DATA              COPY public.hotel (id, name, mail, mpno, star, adress, parking, wifi, pool, fitness, concierge, spa, room_service) FROM stdin;
    public          postgres    false    203   I0       �          0    16471    pension 
   TABLE DATA           �   COPY public.pension (id, hotel_id, ultra_all_inc, all_inc, bed_breakfast, full_board, half_board, room_only, full_board_e_alcohol) FROM stdin;
    public          postgres    false    205   b1       �          0    16495    reservation 
   TABLE DATA           D   COPY public.reservation (id, name, mpno, mail, room_id) FROM stdin;
    public          postgres    false    210   �1       �          0    16485    room 
   TABLE DATA           �   COPY public.room (id, hotel_id, pansion_id, season_id, room_type, stock, adult_price, child_price, bed, square_meter, television, minibar, game_console, cash_case, projection) FROM stdin;
    public          postgres    false    207   �1       �          0    16523    season 
   TABLE DATA           M   COPY public.season (id, hotel_id, start_date, finish_date, cost) FROM stdin;
    public          postgres    false    213   �2       �          0    16451    user 
   TABLE DATA           :   COPY public."user" (id, username, pass, role) FROM stdin;
    public          postgres    false    201   !3       �           0    0    book_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.book_id_seq', 12, true);
          public          postgres    false    211            �           0    0    hotel_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.hotel_id_seq', 39, true);
          public          postgres    false    202            �           0    0    pension_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.pension_id_seq', 11, true);
          public          postgres    false    204            �           0    0    reservation_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.reservation_id_seq', 1, false);
          public          postgres    false    209            �           0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 10, true);
          public          postgres    false    206            �           0    0    season_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.season_id_seq', 9, true);
          public          postgres    false    212            �           0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 14, true);
          public          postgres    false    200            T           2606    16512    book book_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.book DROP CONSTRAINT book_pkey;
       public            postgres    false    208            N           2606    16468    hotel hotel_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    203            P           2606    16475    pension pension_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    205            V           2606    16502    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    210            R           2606    16489    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    207            X           2606    16527    season season_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    213            L           2606    16458    user user_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    201            Z           2606    16528    season fkey    FK CONSTRAINT     u   ALTER TABLE ONLY public.season
    ADD CONSTRAINT fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) NOT VALID;
 5   ALTER TABLE ONLY public.season DROP CONSTRAINT fkey;
       public          postgres    false    203    2894    213            Y           2606    16516    room hotel_fkey    FK CONSTRAINT     y   ALTER TABLE ONLY public.room
    ADD CONSTRAINT hotel_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) NOT VALID;
 9   ALTER TABLE ONLY public.room DROP CONSTRAINT hotel_fkey;
       public          postgres    false    207    2894    203            �   �   x��R͊�0>O�"/P�L��<���%`A�V0a�/�;�;��N�T�j�I�����C(8��J�_�	��� !�uF�p��"
w�t����ˏ�"��U+~�_�sFk����8� "����П �;/W~������N�}��`P��3Y�BmiN;r�������i�́�Ss�3|X$
0��8�F��L���9$�8����Q�eġ���_l�zb꠯RI��u?q;B�Y)�n      �   	  x�uP�n� �����l�픨�Z)R2x�rM�R�T��P�f�fXc+��Np���R�Z����V(@�o.>ON��U�����돡WT�%�*9�5}�sB��3��F����ZC^Z�lc�:u&UβTB���7�>Z��5�1��i��2�A㏱�Ra0;�~��A^����oEQ��e醿�M�+��Z	�����:�N_k�uڠ诹�,��A�d鶱�&��&%�K��؇�b���QO���˲��1!��ѽ��      �   [   x�]�K� D�3�1�Z>wq�	�D2��/��WK���muR_2��W�㋹���6�*(B�ϔ��a) �ꑉ@Ӭ/��H��t+:      �      x������ � �      �   �   x�]��� E�ۏ1@W�W�b0�%N���/���p�r��rzo;K�5��g��r��M��6d�`D�)_���9�t[ _���O6?�[�*��R�9���\I�=ȽV�,�Γ��q�Wx
��:`���C�:l=��r���\&�͢c��S�^�_����p9!�;C{      �   i   x�u�Q�0C��.3+��w���m�?���Ƀ� �Ti��֊����'�&�$��	�zl4����Ō��6�H��ja���k���P���g@{+�c�ژ�(�+      �   Z   x�3�LL����442�tt����2�L�-�������G��rY �!�14i�445�	AtUr���#���QM24�ș�X������ dH"U     