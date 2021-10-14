/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abraham Cañon
 */
public class Response<T> {
    private boolean success;
    private List<T> data;
    private String message;
    
    public Response() {
        this.initialize();
    }

    public Response(boolean proceso) {
        this.initialize();
        this.success = proceso;
    }

    public Response(boolean proceso, List<T> data) {
        this.initialize();
        this.success = proceso;
        this.data = data;
    }
    
    private void initialize() {
        this.success = false;
        this.data = new ArrayList<T>();
        this.message = "";
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String toJson() {
        return new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create()
            .toJson(this);
    }
}
