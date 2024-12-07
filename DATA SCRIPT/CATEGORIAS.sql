-- Procedimiento para insertar una categor�a
CREATE OR REPLACE PROCEDURE insertar_categoria(
    p_nombre IN VARCHAR2
) IS
BEGIN
    INSERT INTO CATEGORIAS (NOMBRE)
    VALUES (p_nombre);
END insertar_categoria;
/

-- Procedimiento para actualizar una categor�a
CREATE OR REPLACE PROCEDURE actualizar_categoria(
    p_categoria_id IN NUMBER,
    p_nombre IN VARCHAR2
) IS
BEGIN
    UPDATE CATEGORIAS
    SET NOMBRE = p_nombre
    WHERE CATEGORIA_ID = p_categoria_id;
END actualizar_categoria;
/

-- Procedimiento para eliminar una categor�a
CREATE OR REPLACE PROCEDURE eliminar_categoria(
    p_categoria_id IN NUMBER
) IS
BEGIN
    DELETE FROM CATEGORIAS
    WHERE CATEGORIA_ID = p_categoria_id;
END eliminar_categoria;
/

-- Procedimiento para consultar una categor�a por ID
CREATE OR REPLACE PROCEDURE consultar_categoria(
    p_categoria_id IN NUMBER,
    p_resultado OUT SYS_REFCURSOR
) IS
BEGIN
    OPEN p_resultado FOR
        SELECT * FROM CATEGORIAS WHERE CATEGORIA_ID = p_categoria_id;
END consultar_categoria;
/

-- Procedimiento para listar todas las categor�as
CREATE OR REPLACE PROCEDURE listar_categorias(
    p_resultado OUT SYS_REFCURSOR
) IS
BEGIN
    OPEN p_resultado FOR
        SELECT * FROM CATEGORIAS;
END listar_categorias;
/

-- Cursor para TODAS categor�as 
create or replace PROCEDURE SP_READ_CATEGORIAS (
    CURSOR_CATEGORIAS OUT SYS_REFCURSOR
) AS
BEGIN
    OPEN CURSOR_CATEGORIAS FOR
    SELECT CATEGORIA_ID, NOMBRE
    FROM CATEGORIAS;
END SP_READ_CATEGORIAS;

-- LA VISTA

CREATE OR REPLACE VIEW VISTA_CATEGORIAS AS
SELECT 
    CATEGORIA_ID,
    NOMBRE
FROM CATEGORIAS;

-- PAQUETES

CREATE OR REPLACE PACKAGE PKG_GESTION_CATEGORIAS AS
    -- Declaraciones p�blicas
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
    END;

    PROCEDURE actualizar_categoria(p_categoria_id IN NUMBER, p_nombre IN VARCHAR2) IS
    BEGIN
        UPDATE CATEGORIAS
        SET NOMBRE = p_nombre
        WHERE CATEGORIA_ID = p_categoria_id;
    END;

    PROCEDURE eliminar_categoria(p_categoria_id IN NUMBER) IS
    BEGIN
        DELETE FROM CATEGORIAS
        WHERE CATEGORIA_ID = p_categoria_id;
    END;

    PROCEDURE consultar_categoria(p_categoria_id IN NUMBER, p_result OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN p_result FOR
        SELECT * FROM CATEGORIAS WHERE CATEGORIA_ID = p_categoria_id;
    END;

    PROCEDURE listar_categorias(p_result OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN p_result FOR
        SELECT * FROM CATEGORIAS;
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
    END;
END PKG_REPORTES_CATEGORIAS;
/

--CURSORES

-- Cursor para obtener todas las categor�as
DECLARE
    CURSOR cur_categorias IS
        SELECT CATEGORIA_ID, NOMBRE FROM CATEGORIAS;
BEGIN
    FOR categoria IN cur_categorias LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || categoria.CATEGORIA_ID || ' - Nombre: ' || categoria.NOMBRE);
    END LOOP;
END;
/

-- Cursor para buscar categor�as por nombre
DECLARE
    CURSOR cur_categorias_nombre IS
        SELECT CATEGORIA_ID, NOMBRE 
        FROM CATEGORIAS 
        WHERE NOMBRE LIKE 'Tecnolog�a%'; -- Cambiar filtro seg�n necesidad
BEGIN
    FOR categoria IN cur_categorias_nombre LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || categoria.CATEGORIA_ID || ' - Nombre: ' || categoria.NOMBRE);
    END LOOP;
END;
/

