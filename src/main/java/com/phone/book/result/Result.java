package com.phone.book.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * ClassName: Result
 * Description: TODO
 * Author: Leo
 * Date: 2020/5/23-18:45
 * email 1437665365@qq.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result implements Serializable {


    private boolean success;

    private String message;
}
