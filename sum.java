
package javaapplication2.home;
import java.util.*;
public class sum {
    //mean
    static float mean(float arr[]){
        float sum=0;
        for(float i:arr){
            sum+=i;
        }
        return sum/arr.length;
    }
    //median
    static float median(float arr[]){
        Arrays.sort(arr);
        int l=arr.length;
        if(l%2!=0){
            return arr[(l+1)/2];
        }
        else{
            float x=arr[l/2-1]+arr[l/2];
            return x/2;
        }
    }
    //mode
    static float mode(float arr[]){
        float maxValue = 0;
        int maxCount = 0,i, j;
        for (i = 0; i < arr.length; i++) {
            int count = 0;
            for (j = 0; j < arr.length; j++) {
                if (arr[j] == arr[i])
                count++;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = arr[i];
            }
        }
        return maxValue;
    }
    //variance
    static float[] variance(float arr[]){
        float var=0;
        float m=mean(arr);
        for(float i:arr){
            var+=Math.pow(i-m,2);
        }
        float a[]={var/arr.length,var/(arr.length-1)};
        return a;
    }
    //standard deviation
    static float[] standardDeviation(float arr[]){
        float x,y;
        float v[];
        v=variance(arr);
        x=(float)(Math.sqrt(v[0]));
        y=(float)(Math.sqrt(v[1]));
        float a[]={x,y};
        return a;
    }
    //mean deviation
    static float meanDeviation(float arr[]){
        float sum=0;
        float m=mean(arr);
        for(float i:arr){
            sum+=Math.abs(i-m);
        }
        return sum/arr.length;
    }
    //data collection
    //methods collection
    //data conversion
    String[] data(String val, int arr_selected[]){
        String val1 = val.replaceAll(" ", "");
        String[] csval = val1.split(",",val1.length());
        int leng = csval.length;
        float[] arr = new float[leng];
        int i = 0;
        for (String str:csval){
            arr[i] = Float.parseFloat(str);
            i++;
        }
        String mean_value="-";
        String median_value="-";
        String mode_value="-";
        String variance_pop_value="-";
        String variance_sam_value="-";
        String standardDeviation_pop_value="-";
        String standardDeviation_sam_value="-";
        String meanDeviation_value="-";
        for(i=0;i<6;i++){
            if(arr_selected[i]!=0 && i==0){
                mean_value=Float.toString(mean(arr));
            }
            if(arr_selected[i]!=0 && i==1){
                median_value=Float.toString(median(arr));
            }
            if(arr_selected[i]!=0 && i==2){
                mode_value=Float.toString(mode(arr));
            }
            if(arr_selected[i]!=0 && i==3){
                float variance[]=variance(arr);
                variance_pop_value=Float.toString(variance[0]);
                variance_sam_value=Float.toString(variance[1]);
            }
            if(arr_selected[i]!=0 && i==4){
                float standardDeviation_value[]=standardDeviation(arr);
                standardDeviation_pop_value=Float.toString(standardDeviation_value[0]);
                standardDeviation_sam_value=Float.toString(standardDeviation_value[1]);
            }
            if(arr_selected[i]!=0 && i==5){
                meanDeviation_value=Float.toString(meanDeviation(arr));
            }
        }
        String values[]={mean_value,median_value,mode_value,variance_pop_value,variance_sam_value,standardDeviation_pop_value,standardDeviation_sam_value,meanDeviation_value};
        return values;
    }
}
