CREATE OR REPLACE PROCEDURE SP_OBTENER_NOTICIAS_POR_FECHA(
    P_FECHA IN DATE,
    P_RESULTADO OUT SYS_REFCURSOR
) AS
BEGIN
    -- si fecha es nula
    IF P_FECHA IS NULL THEN
        RAISE_APPLICATION_ERROR(-20001, 'La fecha proporcionada no puede ser nula.');
    END IF;
    
    --  cursor con los resultados
    BEGIN
        OPEN P_RESULTADO FOR
            SELECT TITULO, CONTENIDO, ESCRITOR, FECHA_PUBLICADA 
            FROM NOTICIA
            WHERE FECHA_PUBLICADA = P_FECHA;
    EXCEPTION
        WHEN OTHERS THEN
            -- error
            RAISE_APPLICATION_ERROR(-20002, 'Ocurrió un error al obtener las noticias: ' || SQLERRM);
    END;
END SP_OBTENER_NOTICIAS_POR_FECHA;
