package com.qycloud.oatos.server.util;

import com.conlect.oatos.dto.status.ErrorType;

/**
 *对象名称：定义逻辑层异常
 *
 * 作者： 秦利军
 * 
 * 完成日期：
 * 
 * 对象内容：
 */
public class LogicException extends RuntimeException
{

    private static final long serialVersionUID = 1L;
    private String ErroMSG;
    private ErrorType error;

    /**
     * Constructs ...
     *
     */
    public LogicException()
    {
        super();
    }

    /**
     * Constructs ...
     *
     *
     * @param msg
     */
    public LogicException(String msg)
    {
        super(msg);
        this.ErroMSG = msg;
    }
     public LogicException(ErrorType error)
    {
        super(error.toString());
        this.error = error;
    }
      public LogicException(ErrorType error, Throwable cause)
    {
        super(error.toString(),cause);
        this.error = error;
    }

    /**
     * Constructs ...
     *
     *
     * @param cause
     */
    public LogicException(Throwable cause)
    {
        super(cause);
    }

    /**
     * Constructs ...
     *
     *
     * @param msg
     * @param cause
     */
    public LogicException(String msg, Throwable cause)
    {
        super(msg, cause);
        this.ErroMSG = msg;
    }

    /**
     * @return the ErroMSG
     */
    public String getErroMSG()
    {
        return ErroMSG;
    }

    /**
     * @param ErroMSG the ErroMSG to set
     */
    public void setErroMSG(String ErroMSG)
    {
        this.ErroMSG = ErroMSG;
    }

    /**
     * @return the error
     */
    public ErrorType getError()
    {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(ErrorType error)
    {
        this.error = error;
    }
}