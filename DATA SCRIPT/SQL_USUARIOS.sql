CREATE OR REPLACE PROCEDURE SP_CREATE_USUARIO(
    P_USERNAME IN VARCHAR2,
    P_EMAIL IN VARCHAR2,
    P_PASSWORD IN VARCHAR2,
    P_ROLE IN VARCHAR2
) AS
BEGIN
    BEGIN
        INSERT INTO USUARIOS (USERNAME, EMAIL, PASSWORD, ROLE)
        VALUES (P_USERNAME, P_EMAIL, P_PASSWORD, P_ROLE);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: El usuario con el nombre o correo electrónico ya existe.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error desconocido: ' || SQLERRM);
    END;
END SP_CREATE_USUARIO;
/

CREATE OR REPLACE PROCEDURE SP_READ_USUARIOS (
    CURSOR_USUARIOS OUT SYS_REFCURSOR
) AS
BEGIN
    BEGIN
        OPEN CURSOR_USUARIOS FOR
        SELECT USER_ID, USERNAME, EMAIL, ROLE
        FROM USUARIOS;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('No se encontraron usuarios.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error desconocido: ' || SQLERRM);
    END;
END SP_READ_USUARIOS;
/

CREATE OR REPLACE PROCEDURE SP_READ_USUARIO_BY_ID (
    P_USER_ID IN NUMBER,
    CURSOR_USUARIO OUT SYS_REFCURSOR
) AS
BEGIN
    BEGIN
        OPEN CURSOR_USUARIO FOR
        SELECT USER_ID, USERNAME, EMAIL, ROLE
        FROM USUARIOS
        WHERE USER_ID = P_USER_ID;

        IF CURSOR_USUARIO%NOTFOUND THEN
            DBMS_OUTPUT.PUT_LINE('No se encontró el usuario con el ID ' || P_USER_ID);
        END IF;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('No se encontró el usuario con el ID ' || P_USER_ID);
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error desconocido: ' || SQLERRM);
    END;
END SP_READ_USUARIO_BY_ID;
/


CREATE OR REPLACE PROCEDURE SP_UPDATE_USUARIO(
    P_USER_ID IN NUMBER,
    P_USERNAME IN VARCHAR2,
    P_EMAIL IN VARCHAR2,
    P_PASSWORD IN VARCHAR2,
    P_ROLE IN VARCHAR2
) AS
BEGIN
    BEGIN
        UPDATE USUARIOS
        SET USERNAME = P_USERNAME,
            EMAIL = P_EMAIL,
            PASSWORD = P_PASSWORD,
            ROLE = P_ROLE
        WHERE USER_ID = P_USER_ID;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('No se encontró el usuario con el ID ' || P_USER_ID);
        END IF;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('No se encontró el usuario con el ID ' || P_USER_ID);
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error desconocido: ' || SQLERRM);
    END;
END SP_UPDATE_USUARIO;
/


CREATE OR REPLACE PROCEDURE SP_DELETE_USUARIO(
    P_USER_ID IN NUMBER
) AS
BEGIN
    BEGIN
        DELETE FROM USUARIOS
        WHERE USER_ID = P_USER_ID;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('No se encontró el usuario con el ID ' || P_USER_ID);
        END IF;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('No se encontró el usuario con el ID ' || P_USER_ID);
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error desconocido: ' || SQLERRM);
    END;
END SP_DELETE_USUARIO;
/



-- FUNCTION VALIDAR LOGIN

CREATE OR REPLACE FUNCTION validar_usuario(
    p_username IN VARCHAR2,
    p_password IN VARCHAR2
) RETURN NUMBER IS
    v_count NUMBER;
BEGIN
    -- Verificamos si existe un usuario con el nombre de usuario y la contraseña proporcionados
    SELECT COUNT(*) 
    INTO v_count
    FROM USUARIOS
    WHERE USERNAME = p_username
    AND PASSWORD = p_password; -- Deberías usar un hash para la contraseña en la vida real

    -- Si se encontró un usuario, devolvemos 1 (TRUE); si no, devolvemos 0 (FALSE)
    IF v_count > 0 THEN
        RETURN 1;
    ELSE
        RETURN 0;
    END IF;
END validar_usuario;
/



-- RESULTADOS

BEGIN
    SP_CREATE_USUARIO('johndoe', 'john.doe@example.com', 'password123', 'admin');
END;
/

-- ------------------------------------------------------------
VARIABLE CURSOR_USUARIOS REFCURSOR;
BEGIN
    SP_READ_USUARIOS(:CURSOR_USUARIOS);
END;
/
PRINT CURSOR_USUARIOS;

-- ------------------------------------------------------------
VARIABLE CURSOR_USUARIO REFCURSOR;
BEGIN
    SP_READ_USUARIO_BY_ID(1, :CURSOR_USUARIO);
END;
/
PRINT CURSOR_USUARIO;

-- ------------------------------------------------------------
BEGIN
    SP_UPDATE_USUARIO(1, 'john_updated', 'john.updated@example.com', 'newpassword', 'editor');
END;
/

-- ------------------------------------------------------------

BEGIN
    SP_DELETE_USUARIO(1);
END;
/

-- PRUEBA USO LOGIN FUNCTION
DECLARE
    v_is_valid NUMBER;
BEGIN
    -- Llamamos a la función validar_usuario
    v_is_valid := validar_usuario('johndoe', 'password123');
    
    -- Verificamos el resultado de la función
    IF v_is_valid = 1 THEN
        DBMS_OUTPUT.PUT_LINE('Usuario válido');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Usuario o contraseña incorrectos');
    END IF;
END;
/


-- CURSORES

-- Cursor para obtener todos los usuarios:
DECLARE
    CURSOR cur_usuarios IS
        SELECT USER_ID, USERNAME, EMAIL 
        FROM USUARIOS;
BEGIN
    FOR usuario IN cur_usuarios LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || usuario.USER_ID || ' - Nombre: ' || usuario.USERNAME || ' - Email: ' || usuario.EMAIL);
    END LOOP;
END;
/

-- Cursor para listar usuarios con un rol específico:

DECLARE
    CURSOR cur_usuarios_rol IS
        SELECT USER_ID, USERNAME, ROLE 
        FROM USUARIOS 
        WHERE ROLE = 'Admin'; -- Cambia "Admin" por el rol deseado
BEGIN
    FOR usuario IN cur_usuarios_rol LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || usuario.USER_ID || ' - Nombre: ' || usuario.USERNAME || ' - Rol: ' || usuario.ROLE);
    END LOOP;
END;
/

-- Cursor para usuarios con correos electrónicos de un dominio específico:

DECLARE
    CURSOR cur_usuarios_dominio IS
        SELECT USER_ID, USERNAME, EMAIL 
        FROM USUARIOS 
        WHERE EMAIL LIKE '%@gmail.com'; -- Cambia por el dominio deseado
BEGIN
    FOR usuario IN cur_usuarios_dominio LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || usuario.USER_ID || ' - Nombre: ' || usuario.USERNAME || ' - Email: ' || usuario.EMAIL);
    END LOOP;
END;
/


-- VISTAS

-- 1. Vista para listar todos los usuarios con su información básica


CREATE OR REPLACE VIEW VISTA_USUARIOS_BASICO AS
SELECT 
    USER_ID,
    USERNAME,
    EMAIL,
    ROLE
FROM 
    USUARIOS;
/

-- 2. Vista para usuarios filtrados por rol

CREATE OR REPLACE VIEW VISTA_USUARIOS_ADMIN AS
SELECT 
    USER_ID,
    USERNAME,
    EMAIL,
    ROLE
FROM 
    USUARIOS
WHERE 
    ROLE = 'Admin';
/

-- 3. Vista para usuarios con correos electrónicos de un dominio específico

CREATE OR REPLACE VIEW VISTA_USUARIOS_GMAIL AS
SELECT 
    USER_ID,
    USERNAME,
    EMAIL
FROM 
    USUARIOS
WHERE 
    EMAIL LIKE '%@gmail.com';
/

-- FUNCIONES

-- 1. Función para verificar si un usuario existe

CREATE OR REPLACE FUNCTION usuario_existe(
    p_username IN VARCHAR2
) RETURN BOOLEAN IS
    v_count NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM USUARIOS
    WHERE USERNAME = p_username;

    RETURN v_count > 0;
END usuario_existe;
/

-- USO
DECLARE
    v_existe BOOLEAN;
BEGIN
    v_existe := usuario_existe('JuanPerez');
    IF v_existe THEN
        DBMS_OUTPUT.PUT_LINE('El usuario existe.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('El usuario no existe.');
    END IF;
END;
/

--2. Función para obtener el rol de un usuario
CREATE OR REPLACE FUNCTION obtener_rol_usuario(
    p_username IN VARCHAR2
) RETURN VARCHAR2 IS
    v_role VARCHAR2(50);
BEGIN
    SELECT ROLE
    INTO v_role
    FROM USUARIOS
    WHERE USERNAME = p_username;

    RETURN v_role;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 'Usuario no encontrado';
END obtener_rol_usuario;
/


-- USO

DECLARE
    v_rol VARCHAR2(50);
BEGIN
    v_rol := obtener_rol_usuario('JuanPerez');
    DBMS_OUTPUT.PUT_LINE('Rol del usuario: ' || v_rol);
END;
/


-- 3. Función para contar usuarios por rol
CREATE OR REPLACE FUNCTION contar_usuarios_por_rol(
    p_role IN VARCHAR2
) RETURN NUMBER IS
    v_count NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM USUARIOS
    WHERE ROLE = p_role;

    RETURN v_count;
END contar_usuarios_por_rol;
/


-- USO
DECLARE
    v_cantidad NUMBER;
BEGIN
    v_cantidad := contar_usuarios_por_rol('Admin');
    DBMS_OUTPUT.PUT_LINE('Cantidad de usuarios con rol Admin: ' || v_cantidad);
END;
/




