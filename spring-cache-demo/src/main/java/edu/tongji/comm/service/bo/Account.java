package edu.tongji.comm.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: chenkangqiang
 * @Date: 2019-07-21
 */
@Data
@AllArgsConstructor
public class Account implements Serializable {
    private static final long serialVersionUID = 3020707126537963057L;
    private int id;
    private String name;
}
