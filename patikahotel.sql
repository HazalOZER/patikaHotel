PGDMP                  	        |            patikahotel    13.13    13.13 $    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
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
    public          postgres    false    207   J,       �          0    16523    season 
   TABLE DATA           G   COPY public.season (id, hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    211   �-       �          0    16451    user 
   TABLE DATA           :   COPY public."user" (id, username, pass, role) FROM stdin;
    public          postgres    false    201   ,.       �           0    0    book_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.book_id_seq', 23, true);
          public          postgres    false    209            �           0    0    hotel_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.hotel_id_seq', 41, true);
          public          postgres    false    202            �           0    0    pension_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.pension_id_seq', 13, true);
          public          postgres    false    204            �           0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 26, true);
          public          postgres    false    206            �           0    0    season_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.season_id_seq', 16, true);
          public          postgres    false    210            �           0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 17, true);
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
       public          postgres    false    2887    203    207            �   �   x�m�A
�0EדS� �L&����� x7A��k�R#��	���Y����@\�hZ~6��6a�X޺ �Q���� ��������ߛ��@H�@/c�AD��D��y�?���`�k���\�t��eA�%F\�b�!`�����DkAR�m��dݮ��%�ɮ��Z2ƾ��J�      �   4  x�U��n� ���)x��D��VU+EM�L��\c�D��b\)y����)��`�X.'�_��|:2�;�5}�Nj��g����5��ipX�W��^55�O���]}�2�K��rp16ː��5]k�լ�z�(�]990XK�~��P��VG8�`�1�Lw��qn?�;(�P���3+uª��v�<�(�b2�N��/��,�	��ҕ��]d~�b��h��ԨAu�?\�,[��t^9�/��z'"R�Q��*��G�| |�����Z��͈$��ެ����r�}L�<�����<nLD6	!�ī�.      �   b   x�]��� C��0!|w�	��
�P�|��X (�zJ-̷�I}��F���P̓t��뫊4�L��b�i��4���,�ٙ���΃�cQ3V      �   7  x�u�Kn�0���Sp��m�l�TU�Rvݠ�JYE��2�M�����TA>��.@��<�{^��@ѝ������2}LUw� 4p��=?�<�Q6\�m�Nӭ��������ue����c7<�гܞ��7���Ce�֠ɬ��52G�԰�@h?�o�5ح=�����j� �ʉ�%��m�< ��%a]�b�ѩ���'%�8&%���Ҩ��b2�Ta:�\���,�P^����)7�Z _������I�.{�\��l6�88�0����
�����
;C�X��o�I����!M��}�����\      �   �   x�u�K�0D�pW~i����#8������0 �t�֑5�7K�(DI�:���.����� N������S�k�q�x �ڗ��S"�?���[r6Vm��c��o"BH����5��*�������2�=����b��9J;      �   `   x�3�LL����442�tt����2�L�-�������G��r���q�$#- z�5��t��5��X�Y��V��$a4 I3H/�Vs�HJb���� Sz,�     