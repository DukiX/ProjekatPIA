/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

/**
 *
 * @author Dusan
 */
@ManagedBean
@SessionScoped
public class Dash implements Serializable{
    private DashboardModel model;

    @PostConstruct
    public void init() {
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
         
        column1.addWidget("dijagram");
 
        model.addColumn(column1);
        column1.addWidget("dijagram2");
 
        model.addColumn(column1);
    }
    
    public DashboardModel getModel() {
        return model;
    }
}
