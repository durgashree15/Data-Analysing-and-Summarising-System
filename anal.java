
package javaapplication2.home;

public class anal {
    static float[] covariance(float num1[], float num2[]){
        float x, y, cv=0;
        int l, i;
        l=num1.length;
        float xd[]=new float[l];
        float yd[]=new float[l];
        float sum1=0, sum2=0;
        for(i=0; i<l; i++){
            sum1+=num1[i];
            sum2+=num2[i];
        }
        x=sum1/l;
        y=sum2/l;
        for(i=0; i<l; i++){
            xd[i]+=num1[i]-x;
            yd[i]+=num2[i]-y;
        }
        for(i=0; i<l;i++){
            cv+=xd[i]*yd[i];
        }
        float a[]={cv/l,cv/(l-1)};
        return a;
    }
    static float correlation(float num1[], float num2[]){
        float x, y, cv=0, a;
        int l, i;
        l=num1.length;
        float xd[]=new float[l];
        float yd[]=new float[l];
        float sum1=0, sum2=0;
        for(i=0; i<l; i++){
            sum1+=num1[i];
            sum2+=num2[i];
        }
        x=sum1/l;
        y=sum2/l;
        for(i=0; i<l; i++){
            xd[i]+=num1[i]-x;
            yd[i]+=num2[i]-y;
        }
        for(i=0; i<l;i++){
            cv+=xd[i]*yd[i];
        }
        
        float varx=0, vary=0;
        for(i=0; i<l; i++){
            varx+=Math.pow(num1[i]-x,2);
            vary+=Math.pow(num2[i]-y,2);
        }
        x=(float)(Math.sqrt(varx));
        y=(float)(Math.sqrt(vary));
        a=cv/(x*y);
        return a;
    }
    static float PointBiSerial(float cat[], float num[]){
        int sum0=0, count0=0, sum1=0, count1=0, mean=0;
        for(int i=0;i<cat.length;i++){
            if((int)cat[i]==0){
                sum0+=num[i];
                count0++;
            }
            else{
                sum1+=num[i];
                count1++;
            }
        }
        float var=0,pbs=0;
        mean=(sum0+sum1)/2;
        for(float i:num){
            var+=Math.pow(i-mean,2);
        }
        float std=(float)Math.sqrt(var/num.length);
        pbs=((sum0/count0)-(sum1/count1))/std;
        return Math.abs(pbs*(float)Math.sqrt(count0*count1/num.length*num.length));
    }
    String[] data2(String arr1, String arr2,int arr[]){
        String cval1= arr1.replaceAll(" ", "");
        String cval2= arr2.replaceAll(" ", "");
        String[] csval1 = cval1.split(",",cval1.length());
        String[] csval2 = cval2.split(",",cval2.length());
        int leng1 = csval1.length;
        int leng2 = csval2.length;
        float[] num1 = new float[leng1];
        float[] num2 = new float[leng2];
        int i = 0;
        for (String str:csval1){
            num1[i] = Float.parseFloat(str);
            i++;
        }
        i=0;
        for (String str:csval2){
            num2[i] = Float.parseFloat(str);
            i++;
        }
        String covar_pop_value="-";
        String covar_sam_value="-";
        String corr_value="-";
        String point_bi_value="-";
        for(i=0;i<3;i++){
            if(arr[i]!=0 && i==0){
                float covariance[]=covariance(num1,num2);
                covar_pop_value=Float.toString(covariance[0]);
                covar_sam_value=Float.toString(covariance[1]);
            }
            if(arr[i]!=0 && i==1){
                corr_value=Float.toString(correlation(num1,num2));
            }
            if(arr[i]!=0 && i==2){
                point_bi_value=Float.toString(PointBiSerial(num1,num2));
            }
        }
        
        String values[]={covar_pop_value,covar_sam_value,corr_value,point_bi_value};
        return values;
    }
}
