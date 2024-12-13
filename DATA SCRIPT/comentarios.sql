CREATE OR REPLACE PROCEDURE SP_CALIFICAR_NOTICIA(
    P_NEWS_ID INT,
    P_USER_ID INT,
    P_ESTRELLAS INT
)
AS
BEGIN
    IF P_ESTRELLAS BETWEEN 1 AND 7 THEN
        INSERT INTO CALIFICACION (NEWS_ID, USER_ID, EVALUACION)
        VALUES (P_NEWS_ID, P_USER_ID, P_ESTRELLAS);
    ELSE
        RAISE_APPLICATION_ERROR(-20001, 'La calificacion debe estar entre 1 y 7.');
    END IF;
END SP_CALIFICAR_NOTICIA;
/

CREATE OR REPLACE PROCEDURE SP_COMENTAR_NOTICIA(
    P_NEWS_ID INT,
    P_USER_ID INT,
    P_COMENTARIO VARCHAR2
)
AS
BEGIN
    -- Intentar insertar el comentario en la tabla COMENTARIOS
    BEGIN
        INSERT INTO COMENTARIOS (NEWS_ID, USER_ID, CONTENIDO_COMENTARIO, FECHA_COMENTADA)
        VALUES (P_NEWS_ID, P_USER_ID, P_COMENTARIO, SYSDATE);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            -- Manejo de error si el comentario ya existe
            RAISE_APPLICATION_ERROR(-20001, 'El usuario ya ha comentado esta noticia.');
        WHEN OTHERS THEN
            -- error no previsto
            RAISE_APPLICATION_ERROR(-20002, 'Ocurrió un error al insertar el comentario: ' || SQLERRM);
    END;
END SP_COMENTAR_NOTICIA;
/


