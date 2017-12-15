/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.Candidat;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author Ghassan
 */
public class CandidatFxHelper extends AbstractFxHelper<Candidat>{
    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("CNE", "id"),
            new AbstractFxHelperItem("NOM", "nom"),
            new AbstractFxHelperItem("PRENOM", "prenom"),
            new AbstractFxHelperItem("DATE N", "date_naissance")
        };
    }

    public CandidatFxHelper(TableView<Candidat> table, List<Candidat> list) {
        super(titres, table, list);
    }

    public CandidatFxHelper(TableView<Candidat> table) {
        super(titres, table);
    }
}
