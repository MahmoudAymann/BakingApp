package com.example.mayman.bakingtestjson;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MahmoudAyman on 8/12/2017.
 */

class BakingAsyncTask extends AsyncTask<String, Void, List<BakingObjs>> {
    private listner1 mCallBack;

    BakingAsyncTask(listner1 mCallBack)

    {
        this.mCallBack = mCallBack;
    }

    private final String LOG_TAG = BakingAsyncTask.class.getSimpleName();

    private List<BakingObjs> getProductDataFromJson(String productsJSONString) throws JSONException {

        BakingObjs bakingObjs;

        //BakingObjs
        final String NAME = "name";
        final String ID = "id";
        final String SERVINGS = "servings";
        final String IMAGE = "image";

        final String QUANTITY = "quantity";
        final String MEASURE = "measure";
        final String INGREDIENT = "ingredient";

        //StepsObjs
        final String STEP_ID = "id";
        final String SHORT_DESCRIPTION = "shortDescription";
        final String DESCRIPTION = "description";
        final String VIDEO_URL = "videoURL";
        final String THUMBNAIL = "thumbnailURL";

        JSONObject product, ingredJsonObj, stepsJsonObj;
        JSONArray jsonArray1 = new JSONArray(productsJSONString);
        JSONArray jsonArray2, jsonArray3;


        List<BakingObjs> bakingObjsList = new ArrayList<>();

        for (int i = 0; i < jsonArray1.length(); i++)
        {// patse every recpi

            product = jsonArray1.getJSONObject(i);
            jsonArray2 = product.getJSONArray("ingredients");
            jsonArray3 = product.getJSONArray("steps");
            Log.v("CAPT", jsonArray3.length() + "  ");

            String name = product.getString(NAME);
            String id = product.getString(ID);
            String servings = product.getString(SERVINGS);
            String image = product.getString(IMAGE);


            ArrayList<String> dataIng = new ArrayList<>();
            ArrayList<StepObjs> stepObjsArrayList = new ArrayList<>();
            //Ingredients parsing
            for (int ii = 0; ii < jsonArray2.length(); ii++)
            {

                ingredJsonObj = jsonArray2.getJSONObject(ii);

                String quantity = ingredJsonObj.getString(QUANTITY);
                String measure = ingredJsonObj.getString(MEASURE);
                String ingredient = ingredJsonObj.getString(INGREDIENT);

                dataIng.add(ingredient + '\t' + quantity + '\t' + measure);
                //Log.v("xxxx", quantity + " " + measure + "rrrrr" + name);
            }//end 2nd for loop

            for (int iii = 0; iii < jsonArray3.length(); iii++)
            {
                StepObjs stepObjs = new StepObjs();
                stepsJsonObj = jsonArray3.getJSONObject(iii);

                String step_id = stepsJsonObj.getString(STEP_ID);
                String short_description = stepsJsonObj.getString(SHORT_DESCRIPTION);
                String description = stepsJsonObj.getString(DESCRIPTION);
                String video_url = stepsJsonObj.getString(VIDEO_URL);
                String thumbnailURL = stepsJsonObj.getString(THUMBNAIL);

                    stepObjs.setStepId(step_id);
                    stepObjs.setShortDescription(short_description);
                    stepObjs.setDescription(description);
                    stepObjs.setVideoURL(video_url);
                    stepObjs.setThumbnailURL(thumbnailURL);

                 stepObjsArrayList.add(stepObjs);

                Log.v("xxxx", step_id + " rrr" + short_description + description);
                Log.v("here",stepObjs.getStepId());
            }//end for 3rd loop


            bakingObjs = new BakingObjs(id, name, servings,image);

            bakingObjs.setIngrediantList(dataIng);
            bakingObjs.setStepObjsList(stepObjsArrayList);

            bakingObjsList.add(bakingObjs);

            Log.v("CAPTURE", bakingObjs.getName());

        }//end for 1st loop
        return bakingObjsList;


    }//end method getProductDataFromJson


    @Override
    protected List<BakingObjs> doInBackground(String... params) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        String productsJSONStrin = null;

        try {

            URL url = new URL("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuilder stringBuffer = new StringBuilder();
            if (inputStream == null) {
                return null;
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                stringBuffer.append(line).append("\n");
            }

            if (stringBuffer.length() == 0) {
                return null;
            }
            productsJSONStrin = stringBuffer.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (final Exception ignored) {
                }
            }
        }//end finally

        try {
            return getProductDataFromJson(productsJSONStrin);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }//end catch

        return null;
    }//end doInBackground()

    @Override
    protected void onPostExecute(List<BakingObjs> strings) {
        mCallBack.onTaskComplete(strings);
    }//end onPostExecute()


}//end class MovieTask