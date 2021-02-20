package pl.wojtek;

import java.util.List;

public class ResultsPrinter {
private List<String> results;

public ResultsPrinter(List<String> results){
    this.results = results;
}

    public void printResults(){
        System.out.println("Search results");
        for(String key: results ){
            System.out.println("found key : " + key);
        }
}

}
