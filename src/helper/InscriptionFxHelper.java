/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.Inscription;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author Ghassan
 */
public class InscriptionFxHelper extends AbstractFxHelper<Inscription>{
    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("INSCRIPTION", "id"),
            new AbstractFxHelperItem("FILIERE", "filiere")
            
        };
    }

    public InscriptionFxHelper(TableView<Inscription> table, List<Inscription> list) {
        super(titres, table, list);
    }

    public InscriptionFxHelper(TableView<Inscription> table) {
        super(titres, table);
    }
}
