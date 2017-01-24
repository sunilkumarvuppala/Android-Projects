package com.example.spandanaravulapalli.group12_hw03;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by spandanaravulapalli on 6/11/16.
 */
public class AppsUtil {

    static public class AppPullParser {
        static ArrayList<App> parseApps(InputStream inputStream) throws XmlPullParserException, IOException {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(inputStream, "UTF-8");
            ArrayList<App> appArrayList = new ArrayList<>();
            App app = null;
            String nameSpace=xpp.getNamespace("im");
            // String s=xpp.getAttributeValue(nameSpace,"id");
            boolean insideItem=false;
            int eventType = xpp.getEventType();
            Log.d("demo", "before while");
            while (eventType != XmlPullParser.END_DOCUMENT){
                if (eventType == XmlPullParser.START_TAG){
                    if (xpp.getName().equalsIgnoreCase("entry")){
                        insideItem = true;
                        app = new App();
                    }
                    else if (xpp.getName().equalsIgnoreCase("id")){
                        if(insideItem){
                            //  xpp.require(XmlPullParser.START_TAG, null, "id");
                            //   String nameSpace=xpp.getNamespace("im");
                            String s=xpp.getAttributeValue(nameSpace,"id");
                            //   Log.d("demo","name space is    "+nameSpace);
                            app.setId(s);
                        }

                    }
                    else if (xpp.getName().equalsIgnoreCase("title")){
                        if (insideItem){
                            String s=xpp.nextText();
                            Log.d("demo",s);
                            app.setAppTitle(s);
                        }
                    }

                    else if (xpp.getName().equalsIgnoreCase("category")){
                        if (insideItem) {
                            String s = xpp.getAttributeValue(nameSpace, "term");
                            Log.d("demo", s);
                            app.setCategory(s);
                        }
                    }


                    else if (xpp.getName().equalsIgnoreCase("link")){
                        if (insideItem) {
                            String s = xpp.getAttributeValue(null, "href");
                            Log.d("demo", s);
                            app.setUrl(s);
                        }
                    }
                    else if (xpp.getName().equalsIgnoreCase("artist")){
                        if(insideItem){
                            //    String dev = xpp.getAttributeValue(nameSpace,"artist");
                            String s=xpp.nextText();
                            Log.d("demo",s);
                            app.setDevName(s);
                        }
                    }
                    else if (xpp.getName().equalsIgnoreCase("price")){
                        if(insideItem){
                            double s = Double.parseDouble(xpp.getAttributeValue(null, "amount"));
                            Log.d("demo", s+"");
                            app.setPrice(s);
                        }
                    }
                    else if(xpp.getName().equalsIgnoreCase("releaseDate")){
                        if(insideItem){
                            String s =  xpp.getAttributeValue(null,"label");
                            Log.d("release", s);
                            app.setReleaseDate(s);
                            //xpp.nextTag();

                        }
                    }
                    else if (xpp.getName().equalsIgnoreCase("image")){
                        if(insideItem){
                            String s1, s2;
                            if(xpp.getAttributeValue(nameSpace,"height").equals("53")){
                                s1=xpp.nextText();
                                Log.d("demo", s1);
                                app.setSmallImage(s1);
                                xpp.nextTag();
                            }
                            if(xpp.getAttributeValue(nameSpace,"height").equals("100")){
                                s2=xpp.nextText();
                                Log.d("demo", s2);
                                app.setLargeImage(s2);
                            }

                        }
                    }


                }
                else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("entry"))
                {
                    insideItem = false;
                    appArrayList.add(app);
                    app=null;
                }
                eventType = xpp.next();
            }
            return appArrayList;
        }

    }


}


