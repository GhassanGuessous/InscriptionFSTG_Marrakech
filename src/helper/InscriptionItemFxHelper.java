/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.InscriptionItem;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author Ghassan
 */
public class InscriptionItemFxHelper extends AbstractFxHelper<InscriptionItem>{
    
    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("NOMBRE FOURNI", "nombrePieceFournis")
        };
    }

    public InscriptionItemFxHelper(TableView<InscriptionItem> table, List<InscriptionItem> list) {
        super(titres, table, list);
    }

    public InscriptionItemFxHelper(TableView<InscriptionItem> table) {
        super(titres, table);
    }
    
}
