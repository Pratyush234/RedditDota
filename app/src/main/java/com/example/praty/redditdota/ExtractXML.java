package com.example.praty.redditdota;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ExtractXML {

    private static final String TAG = "ExtractXML";

    private String xml;
    private String tag;
    private String endtag;
    private List<String> hrefs=new ArrayList<String>();

    public ExtractXML(String xml, String tag, String endtag) {
        this.xml = xml;
        this.tag = tag;
        this.endtag=endtag;

    }

    public ExtractXML(String xml, String tag) {
        this.xml = xml;
        this.tag = tag;
        this.endtag="NONE";
    }

    public List<String> getHrefs() {
        return hrefs;
    }

    public void setHrefs(List<String> hrefs) {
        this.hrefs = hrefs;
    }

    public void startExtract(){
        List<String> result=new ArrayList<>();
        String[] splitXML=null;
        String marker=null;

        if(endtag.equals("NONE")){
            marker="\"";
            splitXML=xml.split(tag+marker);
        }
        else{
            marker=endtag;
            splitXML=xml.split(tag);

        }

        int count=splitXML.length;

        for(int i=1;i<count;i++){
            String temp=splitXML[i];
          //  Log.d(TAG, "startExtract: temp:"+temp);
            int index=temp.indexOf(marker);
            String temp1=temp.substring(0,index);

            Log.d(TAG, "startExtract: index:"+index);
            Log.d(TAG, "startExtract: extracted:"+temp1);
            result.add(temp1);
        }

        this.hrefs=result;
    }
}
