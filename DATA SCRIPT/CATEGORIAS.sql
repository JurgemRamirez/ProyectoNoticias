-- Procedimiento para insertar una categoria
CREATE OR REPLACE PROCEDURE insertar_categoria(
    p_nombre IN VARCHAR2
) IS
BEGIN
    INSERT INTO CATEGORIAS (NOMBRE)
    VALUES (p_nombre);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'Error al insertar la categoría: ' || SQLERRM);
END insertar_categoria;
/

-- Procedimiento para actualizar una categoria
CREATE OR REPLACE PROCEDURE actualizar_categoria(
    p_categoria_id IN NUMBER,
    p_nombre IN VARCHAR2
) IS
BEGIN
    UPDATE CATEGORIAS
    SET NOMBRE = p_nombre
    WHERE CATEGORIA_ID = p_categoria_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20002, 'Error al actualizar la categoría: ' || SQLERRM);
END actualizar_categoria;
/

-- Procedimiento para eliminar una categoria
CREATE OR REPLACE PROCEDURE eliminar_categoria(
    p_categoria_id IN NUMBER
) IS
BEGIN
    DELETE FROM CATEGORIAS
    WHERE CATEGORIA_ID = p_categoria_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20003, 'Error al eliminar la categoría: ' || SQLERRM);
END eliminar_categoria;
/

-- Procedimiento para consultar una categoria por ID
CREATE OR REPLACE PROCEDURE consultar_categoria(
    p_categoria_id IN NUMBER,
    p_resultado OUT SYS_REFCURSOR
) IS
BEGIN
    OPEN p_resultado FOR
        SELECT * FROM CATEGORIAS WHERE CATEGORIA_ID = p_categoria_id;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004, 'Error al consultar la categoría: ' || SQLERRM);
END consultar_categoria;
/

-- Procedimiento para listar todas las categorias
CREATE OR REPLACE PROCEDURE listar_categorias(
    p_resultado OUT SYS_REFCURSOR
) IS
BEGIN
    OPEN p_resultado FOR
        SELECT * FROM CATEGORIAS;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20005, 'Error al listar las categorías: ' || SQLERRM);
END listar_categorias;
/

-- Cursor para TODAS categorias 
CREATE OR REPLACE PROCEDURE SP_READ_CATEGORIAS (
    CURSOR_CATEGORIAS OUT SYS_REFCURSOR
) AS
BEGIN
    OPEN CURSOR_CATEGORIAS FOR
    SELECT CATEGORIA_ID, NOMBRE
    FROM CATEGORIAS;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20006, 'Error al leer las categorías: ' || SQLERRM);
END SP_READ_CATEGORIAS;
/

-- LA VISTA
CREATE OR REPLACE VIEW VISTA_CATEGORIAS AS
SELECT 
    CATEGORIA_ID,
    NOMBRE
FROM CATEGORIAS;
/

-- PAQUETES

CREATE OR REPLACE PACKAGE PKG_GESTION_CATEGORIAS AS
    -- Declaraciones publicas
    PROCEDURE insertar_categoria(p_nombre IN VARCHAR2);
    PROCEDURE actualizar_categoria(p_categoria_id IN NUMBER, p_nombre IN VARCHAR2);
    PROCEDURE eliminar_categoria(p_categoria_id IN NUMBER);
    PROCEDURE consultar_categoria(p_categoria_id IN NUMBER, p_result OUT SYS_REFCURSOR);
    PROCEDURE listar_categorias(p_result OUT SYS_REFCURSOR);
END PKG_GESTION_CATEGORIAS;
/

CREATE OR REPLACE PACKAGE BODY PKG_GESTION_CATEGORIAS AS
    PROCEDURE insertar_categoria(p_nombre IN VARCHAR2) IS
    BEGIN
        INSERT INTO CATEGORIAS (NOMBRE)
        VALUES (p_nombre);
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE_APPLICATION_ERROR(-20001, 'Error al insertar la categoría: ' || SQLERRM);
    END;

    PROCEDURE actualizar_categoria(p_categoria_id IN NUMBER, p_nombre IN VARCHAR2) IS
    BEGIN
        UPDATE CATEGORIAS
        SET NOMBRE = p_nombre
        WHERE CATEGORIA_ID = p_categoria_id;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE_APPLICATION_ERROR(-20002, 'Error al actualizar la categoría: ' || SQLERRM);
    END;

    PROCEDURE eliminar_categoria(p_categoria_id IN NUMBER) IS
    BEGIN
        DELETE FROM CATEGORIAS
        WHERE CATEGORIA_ID = p_categoria_id;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE_APPLICATION_ERROR(-20003, 'Error al eliminar la categoría: ' || SQLERRM);
    END;

    PROCEDURE consultar_categoria(p_categoria_id IN NUMBER, p_result OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN p_result FOR
        SELECT * FROM CATEGORIAS WHERE CATEGORIA_ID = p_categoria_id;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20004, 'Error al consultar la categoría: ' || SQLERRM);
    END;

    PROCEDURE listar_categorias(p_result OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN p_result FOR
        SELECT * FROM CATEGORIAS;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20005, 'Error al listar las categorías: ' || SQLERRM);
    END;
END PKG_GESTION_CATEGORIAS;
/

CREATE OR REPLACE PACKAGE PKG_REPORTES_CATEGORIAS AS
    PROCEDURE contar_categorias(p_result OUT NUMBER);
END PKG_REPORTES_CATEGORIAS;
/

CREATE OR REPLACE PACKAGE BODY PKG_REPORTES_CATEGORIAS AS
    PROCEDURE contar_categorias(p_result OUT NUMBER) IS
    BEGIN
        SELECT COUNT(*) INTO p_result FROM CATEGORIAS;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20007, 'Error al contar las categorías: ' || SQLERRM);
    END;
END PKG_REPORTES_CATEGORIAS;
/

--CURSORES

-- Cursor para obtener todas las categorias
DECLARE
    CURSOR cur_categorias IS
        SELECT CATEGORIA_ID, NOMBRE FROM CATEGORIAS;
BEGIN
    FOR categoria IN cur_categorias LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || categoria.CATEGORIA_ID || ' - Nombre: ' || categoria.NOMBRE);
    END LOOP;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error al obtener las categorías: ' || SQLERRM);
END;
/

-- Cursor para buscar categorias por nombre
DECLARE
    CURSOR cur_categorias_nombre IS
        SELECT CATEGORIA_ID, NOMBRE 
        FROM CATEGORIAS 
        WHERE NOMBRE LIKE 'Tecnología%'; -- Cambiar filtro según necesidad
BEGIN
    FOR categoria IN cur_categorias_nombre LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || categoria.CATEGORIA_ID || ' - Nombre: ' || categoria.NOMBRE);
    END LOOP;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error al buscar las categorías: ' || SQLERRM);
END;
/

