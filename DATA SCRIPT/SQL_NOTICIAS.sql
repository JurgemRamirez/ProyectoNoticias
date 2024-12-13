
-- GRANT CREATE VIEW TO PROYTECTOLGDB;

-- PA
-- Procedimiento para insertar una noticia
CREATE OR REPLACE PROCEDURE insertar_noticia(
    p_titulo IN VARCHAR2,
    p_contenido IN CLOB,
    p_escritor IN VARCHAR2,
    p_fecha_publicada IN DATE,
    p_categoria_id IN NUMBER
) IS
BEGIN
    BEGIN
        INSERT INTO NOTICIA (TITULO, CONTENIDO, ESCRITOR, FECHA_PUBLICADA, CATEGORIA_ID)
        VALUES (p_titulo, p_contenido, p_escritor, p_fecha_publicada, p_categoria_id);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20001, 'La noticia con este título ya existe.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20002, 'Error al insertar la noticia: ' || SQLERRM);
    END;
END insertar_noticia;
/


-- Procedimiento para actualizar una noticia
CREATE OR REPLACE PROCEDURE actualizar_noticia(
    p_news_id IN NUMBER,
    p_titulo IN VARCHAR2,
    p_contenido IN CLOB,
    p_escritor IN VARCHAR2,
    p_fecha_publicada IN DATE,
    p_categoria_id IN NUMBER
) IS
BEGIN
    BEGIN
        UPDATE NOTICIA
        SET TITULO = p_titulo,
            CONTENIDO = p_contenIDO,
            ESCRITOR = p_escritor,
            FECHA_PUBLICADA = p_fecha_publicada,
            CATEGORIA_ID = p_categoria_id
        WHERE NEWS_ID = p_news_id;
        
        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20003, 'No se encontró la noticia con el ID proporcionado.');
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20004, 'Error al actualizar la noticia: ' || SQLERRM);
    END;
END actualizar_noticia;
/


-- Procedimiento para eliminar una noticia
CREATE OR REPLACE PROCEDURE eliminar_noticia(p_news_id IN NUMBER) IS
BEGIN
    BEGIN
        DELETE FROM NOTICIA WHERE NEWS_ID = p_news_id;
        
        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20005, 'No se encontró la noticia con el ID proporcionado.');
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20006, 'Error al eliminar la noticia: ' || SQLERRM);
    END;
END eliminar_noticia;
/


-- Procedimiento para consultar una noticia por ID
CREATE OR REPLACE PROCEDURE consultar_noticia(
    p_news_id IN NUMBER,
    p_resultado OUT SYS_REFCURSOR
) IS
BEGIN
    BEGIN
        OPEN p_resultado FOR
            SELECT * FROM NOTICIA WHERE NEWS_ID = p_news_id;
        
        -- Verifi  no devuelve resultados
        IF p_resultado%ISOPEN AND p_resultado%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20007, 'No se encontró la noticia con el ID proporcionado.');
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20008, 'Error al consultar la noticia: ' || SQLERRM);
    END;
END consultar_noticia;
/


-- Procedimiento para listar todas las noticias
CREATE OR REPLACE PROCEDURE listar_noticias(
    p_resultado OUT SYS_REFCURSOR
) IS
BEGIN
    BEGIN
        OPEN p_resultado FOR
            SELECT * FROM NOTICIA;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20009, 'Error al listar las noticias: ' || SQLERRM);
    END;
END listar_noticias;
/

-- Procedimiento para listar todas las noticias por termino

CREATE OR REPLACE PROCEDURE consultar_noticia_TermTitulo(
    p_title_pattern IN VARCHAR2, 
    p_resultado OUT SYS_REFCURSOR
) IS
BEGIN
    BEGIN
        OPEN p_resultado FOR
            SELECT * 
            FROM NOTICIA 
            WHERE TITULO LIKE '%' || p_title_pattern || '%';
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20010, 'Error al consultar las noticias por título: ' || SQLERRM);
    END;
END consultar_noticia_TermTitulo;
/


-- VISTA
CREATE OR REPLACE VIEW VISTA_NOTICIAS AS
SELECT 
    NEWS_ID,
    TITULO,
    SUBSTR(CONTENIDO, 1, 100) AS CONTENIDO_RESUMEN,
    ESCRITOR,
    FECHA_PUBLICADA,
    CATEGORIA_ID
FROM NOTICIA;


-- -----------------------------
-- PAQUETES
 -- 1. Paquete PKG_GESTION_NOTICIAS
-- Este paquete maneja las operaciones CRUD principales para la tabla NOTICIA.

CREATE OR REPLACE PACKAGE PKG_GESTION_NOTICIAS AS
    -- Declaraciones públicas
    PROCEDURE insertar_noticia(p_titulo IN VARCHAR2, p_contenido IN CLOB, p_escritor IN VARCHAR2, p_fecha_publicada IN DATE, p_categoria_id IN NUMBER);
    PROCEDURE actualizar_noticia(p_news_id IN NUMBER, p_titulo IN VARCHAR2, p_contenido IN CLOB, p_escritor IN VARCHAR2, p_fecha_publicada IN DATE, p_categoria_id IN NUMBER);
    PROCEDURE eliminar_noticia(p_news_id IN NUMBER);
    PROCEDURE consultar_noticia(p_news_id IN NUMBER, p_result OUT SYS_REFCURSOR);
    PROCEDURE listar_noticias(p_result OUT SYS_REFCURSOR);
END PKG_GESTION_NOTICIAS;
/


CREATE OR REPLACE PACKAGE BODY PKG_GESTION_NOTICIAS AS

    PROCEDURE insertar_noticia(p_titulo IN VARCHAR2, p_contenido IN CLOB, p_escritor IN VARCHAR2, p_fecha_publicada IN DATE, p_categoria_id IN NUMBER) IS
    BEGIN
        BEGIN
            INSERT INTO NOTICIA (TITULO, CONTENIDO, ESCRITOR, FECHA_PUBLICADA, CATEGORIA_ID)
            VALUES (p_titulo, p_contenido, p_escritor, p_fecha_publicada, p_categoria_id);
        EXCEPTION
            WHEN DUP_VAL_ON_INDEX THEN
                RAISE_APPLICATION_ERROR(-20001, 'Error: Ya existe una noticia con el mismo título.');
            WHEN OTHERS THEN
                RAISE_APPLICATION_ERROR(-20002, 'Error inesperado al insertar la noticia: ' || SQLERRM);
        END;
    END insertar_noticia;

    PROCEDURE actualizar_noticia(p_news_id IN NUMBER, p_titulo IN VARCHAR2, p_contenido IN CLOB, p_escritor IN VARCHAR2, p_fecha_publicada IN DATE, p_categoria_id IN NUMBER) IS
    BEGIN
        BEGIN
            UPDATE NOTICIA
            SET TITULO = p_titulo,
                CONTENIDO = p_contenido,
                ESCRITOR = p_escritor,
                FECHA_PUBLICADA = p_fecha_publicada,
                CATEGORIA_ID = p_categoria_id
            WHERE NEWS_ID = p_news_id;

            IF SQL%ROWCOUNT = 0 THEN
                RAISE_APPLICATION_ERROR(-20003, 'Error: No se encontró la noticia con el ID proporcionado.');
            END IF;
        EXCEPTION
            WHEN OTHERS THEN
                RAISE_APPLICATION_ERROR(-20004, 'Error inesperado al actualizar la noticia: ' || SQLERRM);
        END;
    END actualizar_noticia;

    PROCEDURE eliminar_noticia(p_news_id IN NUMBER) IS
    BEGIN
        BEGIN
            DELETE FROM NOTICIA WHERE NEWS_ID = p_news_id;

            IF SQL%ROWCOUNT = 0 THEN
                RAISE_APPLICATION_ERROR(-20005, 'Error: No se encontró la noticia con el ID proporcionado.');
            END IF;
        EXCEPTION
            WHEN OTHERS THEN
                RAISE_APPLICATION_ERROR(-20006, 'Error inesperado al eliminar la noticia: ' || SQLERRM);
        END;
    END eliminar_noticia;

    PROCEDURE consultar_noticia(p_news_id IN NUMBER, p_result OUT SYS_REFCURSOR) IS
    BEGIN
        BEGIN
            OPEN p_result FOR
            SELECT * FROM NOTICIA WHERE NEWS_ID = p_news_id;

            -- Verificar si no se encontró ningún dato
            IF p_result%ROWCOUNT = 0 THEN
                RAISE_APPLICATION_ERROR(-20007, 'No se encontró la noticia con el ID proporcionado.');
            END IF;
        EXCEPTION
            WHEN OTHERS THEN
                RAISE_APPLICATION_ERROR(-20008, 'Error inesperado al consultar la noticia: ' || SQLERRM);
        END;
    END consultar_noticia;

    PROCEDURE listar_noticias(p_result OUT SYS_REFCURSOR) IS
    BEGIN
        BEGIN
            OPEN p_result FOR
            SELECT * FROM NOTICIA;
        EXCEPTION
            WHEN OTHERS THEN
                RAISE_APPLICATION_ERROR(-20009, 'Error inesperado al listar las noticias: ' || SQLERRM);
        END;
    END listar_noticias;

END PKG_GESTION_NOTICIAS;
/


-- 2. Paquete PKG_REPORTES_NOTICIAS
-- Este paquete gestiona reportes y resúmenes de las noticias.

CREATE OR REPLACE PACKAGE PKG_REPORTES_NOTICIAS AS
    PROCEDURE resumen_noticias(p_result OUT SYS_REFCURSOR);
    PROCEDURE noticias_por_categoria(p_categoria_id IN NUMBER, p_result OUT SYS_REFCURSOR);
    PROCEDURE noticias_por_fecha(p_fecha_inicio IN DATE, p_fecha_fin IN DATE, p_result OUT SYS_REFCURSOR);
END PKG_REPORTES_NOTICIAS;
/

CREATE OR REPLACE PACKAGE BODY PKG_REPORTES_NOTICIAS AS

    PROCEDURE resumen_noticias(p_result OUT SYS_REFCURSOR) IS
    BEGIN
        BEGIN
            OPEN p_result FOR
            SELECT NEWS_ID, TITULO, SUBSTR(CONTENIDO, 1, 100) AS CONTENIDO_RESUMEN, ESCRITOR, FECHA_PUBLICADA
            FROM NOTICIA;
            
            -- Verificar si no se encontraron resultados
            IF p_result%ROWCOUNT = 0 THEN
                RAISE_APPLICATION_ERROR(-20001, 'No se encontraron noticias para mostrar.');
            END IF;
        EXCEPTION
            WHEN OTHERS THEN
                RAISE_APPLICATION_ERROR(-20002, 'Error inesperado al obtener el resumen de noticias: ' || SQLERRM);
        END;
    END resumen_noticias;

    PROCEDURE noticias_por_categoria(p_categoria_id IN NUMBER, p_result OUT SYS_REFCURSOR) IS
    BEGIN
        BEGIN
            OPEN p_result FOR
            SELECT * FROM NOTICIA WHERE CATEGORIA_ID = p_categoria_id;

            -- Verificar si no se encontraron resultados
            IF p_result%ROWCOUNT = 0 THEN
                RAISE_APPLICATION_ERROR(-20003, 'No se encontraron noticias para la categoría indicada.');
            END IF;
        EXCEPTION
            WHEN OTHERS THEN
                RAISE_APPLICATION_ERROR(-20004, 'Error inesperado al obtener noticias por categoría: ' || SQLERRM);
        END;
    END noticias_por_categoria;

    PROCEDURE noticias_por_fecha(p_fecha_inicio IN DATE, p_fecha_fin IN DATE, p_result OUT SYS_REFCURSOR) IS
    BEGIN
        BEGIN
            OPEN p_result FOR
            SELECT * FROM NOTICIA WHERE FECHA_PUBLICADA BETWEEN p_fecha_inicio AND p_fecha_fin;

            --  si nohay resultados
            IF p_result%ROWCOUNT = 0 THEN
                RAISE_APPLICATION_ERROR(-20005, 'No se encontraron noticias en el rango de fechas indicado.');
            END IF;
        EXCEPTION
            WHEN OTHERS THEN
                RAISE_APPLICATION_ERROR(-20006, 'Error inesperado al obtener noticias por fecha: ' || SQLERRM);
        END;
    END noticias_por_fecha;

END PKG_REPORTES_NOTICIAS;
/



-- 3. Paquete PKG_UTILS_NOTICIAS
-- Este paquete proporciona utilidades para trabajar con noticias.

CREATE OR REPLACE PACKAGE PKG_UTILS_NOTICIAS AS
    FUNCTION contar_noticias RETURN NUMBER;
    FUNCTION existe_noticia(p_news_id IN NUMBER) RETURN BOOLEAN;
END PKG_UTILS_NOTICIAS;
/

CREATE OR REPLACE PACKAGE BODY PKG_UTILS_NOTICIAS AS

    FUNCTION contar_noticias RETURN NUMBER IS
        v_count NUMBER;
    BEGIN
        BEGIN
            SELECT COUNT(*) INTO v_count FROM NOTICIA;
            RETURN v_count;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                -- No se encontraron noticias
                RETURN 0;
            WHEN OTHERS THEN
                -- Error inesperado
                RAISE_APPLICATION_ERROR(-20001, 'Error al contar las noticias: ' || SQLERRM);
        END;
    END;

    FUNCTION existe_noticia(p_news_id IN NUMBER) RETURN BOOLEAN IS
        v_count NUMBER;
    BEGIN
        BEGIN
            SELECT COUNT(*) INTO v_count FROM NOTICIA WHERE NEWS_ID = p_news_id;
            RETURN v_count > 0;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                -- Si no se encuentra la noticia, devolver FALSE
                RETURN FALSE;
            WHEN OTHERS THEN
                -- Error inesperado
                RAISE_APPLICATION_ERROR(-20002, 'Error al verificar la existencia de la noticia: ' || SQLERRM);
        END;
    END;

END PKG_UTILS_NOTICIAS;
/



-- 4. Paquete PKG_MANTENIMIENTO_NOTICIAS
-- Este paquete incluye procedimientos para mantenimiento, como borrar noticias antiguas.

CREATE OR REPLACE PACKAGE PKG_MANTENIMIENTO_NOTICIAS AS
    PROCEDURE borrar_noticias_antiguas(p_fecha_limite IN DATE);
END PKG_MANTENIMIENTO_NOTICIAS;
/


CREATE OR REPLACE PACKAGE BODY PKG_MANTENIMIENTO_NOTICIAS AS

    PROCEDURE borrar_noticias_antiguas(p_fecha_limite IN DATE) IS
    BEGIN
        BEGIN
            DELETE FROM NOTICIA WHERE FECHA_PUBLICADA < p_fecha_limite;
            
            -- Verifica si se eliminaron registros
            IF SQL%ROWCOUNT = 0 THEN
                RAISE_APPLICATION_ERROR(-20001, 'No se encontraron noticias para eliminar.');
            END IF;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                -- Si no hay noticias que eliminar, no hacer nada
                NULL;
            WHEN OTHERS THEN
                -- Error inesperado
                RAISE_APPLICATION_ERROR(-20002, 'Error al borrar las noticias antiguas: ' || SQLERRM);
        END;
    END;

END PKG_MANTENIMIENTO_NOTICIAS;
/





-- CURSORES

-- Cursor para obtener todas las noticias publicadas después de una fecha específica:
DECLARE
    CURSOR cur_noticias_recientes IS
        SELECT NEWS_ID, TITULO, FECHA_PUBLICADA 
        FROM NOTICIA 
        WHERE FECHA_PUBLICADA > SYSDATE - 30; -- Últimos 30 días

BEGIN
    -- carga
    FOR noticia IN cur_noticias_recientes LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || noticia.NEWS_ID || ' - Título: ' || noticia.TITULO || ' - Fecha: ' || noticia.FECHA_PUBLICADA);
    END LOOP;
    
    -- Si no se encuentran noticias, se puede manejar
    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No se encontraron noticias recientes.');
    END IF;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- En caso de que no haya datos 
        DBMS_OUTPUT.PUT_LINE('No se encontraron noticias recientes.');
    WHEN OTHERS THEN
        --  error inesperado
        DBMS_OUTPUT.PUT_LINE('Error inesperado: ' || SQLERRM);
END;
/


-- Cursor para obtener noticias por categoría:

DECLARE
    CURSOR cur_noticias_categoria IS
        SELECT NEWS_ID, TITULO, CATEGORIA_ID 
        FROM NOTICIA 
        WHERE CATEGORIA_ID = 1; -- Cambia "1" por la categoría deseada
BEGIN
    FOR noticia IN cur_noticias_categoria LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || noticia.NEWS_ID || ' - Título: ' || noticia.TITULO || ' - Categoría: ' || noticia.CATEGORIA_ID);
    END LOOP;

    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No se encontraron noticias para la categoría especificada.');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No se encontraron noticias para la categoría especificada.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error inesperado en el cursor de noticias por categoría: ' || SQLERRM);
END;
/

--Cursor para listar noticias de un escritor específico:


DECLARE
    CURSOR cur_noticias_escritor IS
        SELECT NEWS_ID, TITULO, ESCRITOR 
        FROM NOTICIA 
        WHERE ESCRITOR = 'Jurgem Ramirez'; -- Cambia por el nombre del escritor
BEGIN
    FOR noticia IN cur_noticias_escritor LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || noticia.NEWS_ID || ' - Título: ' || noticia.TITULO || ' - Escritor: ' || noticia.ESCRITOR);
    END LOOP;

    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No se encontraron noticias para el escritor especificado.');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No se encontraron noticias para el escritor especificado.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error inesperado en el cursor de noticias por escritor: ' || SQLERRM);
END;
/



-- Cursor para noticias resumidas (primeros 50 caracteres del contenido):

DECLARE
    CURSOR cur_resumen_noticias IS
        SELECT NEWS_ID, TITULO, SUBSTR(CONTENIDO, 1, 50) AS RESUMEN 
        FROM NOTICIA;
BEGIN
    FOR noticia IN cur_resumen_noticias LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || noticia.NEWS_ID || ' - Título: ' || noticia.TITULO || ' - Resumen: ' || noticia.RESUMEN);
    END LOOP;

    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No se encontraron noticias para mostrar resumen.');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No se encontraron noticias para mostrar resumen.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error inesperado en el cursor de noticias resumidas: ' || SQLERRM);
END;
/





-- Vistas adicionales para cumplir con los requerimientos del proyecto

-- Vista para listar noticias por categoría
CREATE OR REPLACE VIEW VISTA_NOTICIAS_CATEGORIA AS
SELECT 
    N.NOTICIA_ID,
    N.TITULO,
    N.CONTENIDO,
    C.NOMBRE AS CATEGORIA
FROM 
    NOTICIAS N
JOIN 
    CATEGORIAS C ON N.CATEGORIA_ID = C.CATEGORIA_ID;
/

-- Vista para listar noticias mejor calificadas (calificación mayor a 5)
CREATE OR REPLACE VIEW VISTA_NOTICIAS_MEJOR_CALIFICADAS AS
SELECT 
    N.NOTICIA_ID,
    N.TITULO,
    N.CONTENIDO,
    NV.CALIFICACION
FROM 
    NOTICIAS N
JOIN 
    NOTICIAS_VALORACIONES NV ON N.NOTICIA_ID = NV.NOTICIA_ID
WHERE 
    NV.CALIFICACION > 5;
/

-- Vista para listar usuarios que han comentado alguna noticia
CREATE OR REPLACE VIEW VISTA_USUARIOS_COMENTARIOS AS
SELECT 
    DISTINCT U.USER_ID,
    U.USERNAME,
    U.EMAIL
FROM 
    USUARIOS U
JOIN 
    COMENTARIOS C ON U.USER_ID = C.USER_ID;
/

-- Vista para listar noticias publicadas en el último mes
CREATE OR REPLACE VIEW VISTA_NOTICIAS_RECIENTES AS
SELECT 
    NOTICIA_ID,
    TITULO,
    CONTENIDO,
    FECHA_PUBLICACION
FROM 
    NOTICIAS
WHERE 
    FECHA_PUBLICACION >= ADD_MONTHS(SYSDATE, -1);
/

-- Vista para listar usuarios registrados en el último mes
CREATE OR REPLACE VIEW VISTA_USUARIOS_RECIENTES AS
SELECT 
    USER_ID,
    USERNAME,
    EMAIL,
    FECHA_REGISTRO
FROM 
    USUARIOS
WHERE 
    FECHA_REGISTRO >= ADD_MONTHS(SYSDATE, -1);
/

-- Vista para listar noticias con comentarios
CREATE OR REPLACE VIEW VISTA_NOTICIAS_COMENTADAS AS
SELECT 
    N.NOTICIA_ID,
    N.TITULO,
    COUNT(C.COMENTARIO_ID) AS NUMERO_COMENTARIOS
FROM 
    NOTICIAS N
LEFT JOIN 
    COMENTARIOS C ON N.NOTICIA_ID = C.NOTICIA_ID
GROUP BY 
    N.NOTICIA_ID, N.TITULO;
/

-- Vista para listar usuarios con más de una calificación a noticias
CREATE OR REPLACE VIEW VISTA_USUARIOS_MULTIPLES_CALIFICACIONES AS
SELECT 
    U.USER_ID,
    U.USERNAME,
    COUNT(NV.CALIFICACION) AS NUMERO_CALIFICACIONES
FROM 
    USUARIOS U
JOIN 
    NOTICIAS_VALORACIONES NV ON U.USER_ID = NV.USER_ID
GROUP BY 
    U.USER_ID, U.USERNAME
HAVING 
    COUNT(NV.CALIFICACION) > 1;
/





