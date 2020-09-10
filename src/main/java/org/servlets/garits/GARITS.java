package org.servlets.garits;

//__________________________
//--------------------------\
//          |)|   PROVIDED BY\
//          | |       SERVLETS\
//          | |_____    TEAM 26\
//_________/    (]__)___________\
//        /    (]___)            \
//       /    (]___)              \
//      /   ___(]_)                \
//     /   /                        \
//____/   /--------------------------\
import java.sql.Date;
import org.servlets.garits.Accounts.User;
import org.servlets.garits.GUIs.GUIController;
import org.servlets.garits.StockControl.*;
import org.servlets.garits.Job.*;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import org.servlets.garits.Documents.CreateStockReport;

public class GARITS extends Application {

    StockController stockController;
    private ArrayList<Job> pendingJobsList;
    private GUIController GC;
    public User user;
    
    public static void main(String[] args) {
        launch(args);
    }

    public GUIController getGC() {
        return GC;
    }

    @Override
    public void start(Stage stage) throws Exception {

        GC = new GUIController(stage);
        GC.init();
        
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        if (date.toLocalDate().getDayOfMonth() == 1
                || date.toLocalDate().getDayOfMonth() == 14
                || date.toLocalDate().getDayOfMonth() == 28) {
            CreateStockReport s=new CreateStockReport();
        s.CreatePdf();
        }
        
        
        
    }
}
