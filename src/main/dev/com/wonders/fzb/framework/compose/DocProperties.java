package com.wonders.fzb.framework.compose;


import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class DocProperties extends HashMap<String,String> implements java.io.Serializable{
	
    public DocProperties() {
    }
    
    public String toXML() throws ComposeException{
      String ret="";
      try{
        ret="<DOCINFO>";
        Iterator<String> itr=this.keySet().iterator();
        while(itr.hasNext()){
           String keyName=(String)(itr.next());
           String keyValue=(String)this.get(keyName);
           if(keyValue==null)
               keyValue = " ";
           String keyTree;
           keyTree="<"+keyName.toUpperCase()+">"+"<<![CDATA["+keyValue+"]]>"+"</"+keyName.toUpperCase()+">";
           ret=ret+keyTree;
        }
        ret=ret+"</DOCINFO>";
        return ret;
      }catch(Exception e){
          System.out.println("DocProperties.toXML() error: " + e.getMessage());
          throw new ComposeException(e);
      }
    }

    /**
     * 把普通参数编码成符合循环参数命名规则的参数，并添加到本DocProperties中
     * @param dpList 普通参数DocProperties的列表 ArrayList of DocProperties
     * @param loopObjName  循环对象名称(列表变量名称，循环定位符)
     */
    public  void  addLoopDocProperties(ArrayList<DocProperties> dpList,String loopObjName){
        for(int i=0;i<dpList.size();i++){
            String seq = Integer.toString(i);
            DocProperties dps = (DocProperties)dpList.get(i);
            Iterator<String> itr = dps.keySet().iterator();
            while(itr.hasNext()){
               String keyName=(String)(itr.next());
               String keyValue=(String)dps.get(keyName);
               this.put("DS_"+seq+"_"+keyName+"_"+loopObjName,keyValue);
            }
        }
    }

    /**
     * 根据loopObjName获得该loopObjName所对应的值对象的列表
     * @param loopObjName 循环对象名称(列表变量名称，循环定位符)
     * @return ArrayList of DocProperties列表
     */
    public ArrayList<DocProperties> getLoopDocProperties(String loopObjName) {
      ArrayList<DocProperties> dps = new ArrayList<DocProperties>();
      DocProperties dp = null;

      Iterator<String> itr = this.keySet().iterator();
      int max = 0;
      while(itr.hasNext()){
         String keyName=(String)(itr.next());
         if("DS_".equals(keyName.substring(0,3))){
             int count = Integer.parseInt(keyName.substring(3,keyName.indexOf("_",3)));
             max = max>count?max:count;
         }
      }
      for(int i=0;i<=max;i++){
          dp = new DocProperties();
          itr = this.keySet().iterator();
          while(itr.hasNext()){
              String keyName=(String)(itr.next());
              String keyValue=(String)this.get(keyName);
              if(keyName.indexOf("DS_"+Integer.toString(i)+"_")>=0){
                  String lon=("_"+loopObjName).toUpperCase();
                  int pos = keyName.lastIndexOf(lon);
                  if ( (pos!=-1) && (pos +(lon).length())==keyName.length()){
                    keyName = keyName.substring(4 + Integer.toString(i).length(), pos);
                    dp.put(keyName, keyValue);
                  }
              }
          }
          dps.add(dp);
      }
      return dps;

  }

    /**
     * 获得DocProperties中的loopObjName列表，以使composer知道其中有多少种类型的循环对象
     * @return ArrayList of String 循环对象名列表（list名称列表）
     * 处理： 查找DocProperties有多少种类型的循环标记，返回类型列表。
     */
    public ArrayList<String>  getLoopObjNameList(){
        ArrayList<String> ret = new ArrayList<String>();
        Iterator<String> itr = this.keySet().iterator();
        String tempstr = "";
        while(itr.hasNext()){
            String keyName=(String)(itr.next());
            if("DS_".equals(keyName.substring(0,3))){
                String tag = keyName.substring(keyName.lastIndexOf("_")+1);
                if(tempstr.indexOf(tag+"#")<0){
                    tempstr += tag + "#";
                    ret.add(tag);
                }
            }
        }
        return ret;
    }

    /**
     * 获得DocProperties中的书签和普通标记列表
     * @return ArrayList of String 名列表（list名称列表）
     * 处理： 返回DocProperties中除了循环标记之外的所有key列表。
     */
    public ArrayList<String>  getCommonTagList(){
        ArrayList<String> ret = new ArrayList<String>();
        Iterator<String> itr = this.keySet().iterator();
        while(itr.hasNext()){
            String keyName=(String)(itr.next());
            if(!"DS_".equals(keyName.substring(0,3))){
                ret.add(keyName);
            }
        }
        return ret;
    }

}