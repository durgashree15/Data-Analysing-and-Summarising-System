package javaapplication2.home;
import java.util.*;
import java.lang.Math;

/*public class model {

    public static float[] varcalc(float x[], float y[])
    {
        float sumx = 0, sumy = 0, sumdevx = 0, sumdevy = 0, avgx = 0, avgy = 0;
        int n = x.length;
        for (int i = 0; i<n; i++)
        {
            sumx += x[i];
            sumy += y[i];
        }

        avgx = sumx/(n);
        avgy = sumy/(n);

        float devx[];
        float devy[];
        devx = new float[n];
        devy = new float[n];

        for (int i = 0; i<n; i++)
        {
            devx[i] = x[i]-avgx;
            devy[i] = y[i]-avgy;

        }

        for (int j = 0; j<n; j++)
        {
            sumdevx += devx[j];
            sumdevy += devy[j];
        }

        float devxsquare[];
        float devysquare[];
        float devxy[];
        devxsquare = new float[n];
        devysquare = new float[n];
        devxy = new float[n];

        for (int i = 0 ;i<n; i++)
        {
            devxsquare[i] = devx[i]*devx[i];
            devysquare[i] = devy[i]*devy[i];
            devxy[i] = devx[i]*devy[i];
        }

        float sumdevxsquare = 0, sumdevysquare = 0, sumdevxy = 0;

        for (int i = 0; i<n; i++)
        {
            sumdevxsquare += devxsquare[i];
            sumdevysquare += devysquare[i];
            sumdevxy += devxy[i];
        }

        float covxy = (sumdevxy/n) - ((sumdevx/n)*(sumdevy/n));
        float varx = (sumdevxsquare/n)-((sumdevx/n)*(sumdevx/n));
        float vary = (sumdevysquare/n)-((sumdevy/n)*(sumdevy/n));


        float[] ans = new float[]{ covxy,varx,vary,avgx,avgy }; 
        return (ans);
    }

    public static String[] regressionequation (float x[], float y[], int a,float var1)
    {
        float ansset[] = varcalc(x,y);
        
        float covxy = ansset[0];
        float varx = ansset[1];
        float vary = ansset[2];
        float avgx = ansset[3];
        float avgy = ansset[4];

        float byx = covxy/varx;
        float bxy = covxy/vary;

        String output[]={"",""};
        String p="-";
        if (a == 2)
        {
            if (byx>1 || byx<0)
            {
                
                output[0] = "Regression of y on x does not exist";
            }
            else
            {
                output[0] = "y - "+avgy+" = "+byx+" (x - "+avgx+")";
                float q=byx*(var1-avgx)+avgy;
                p=Float.toString(q);
            }
            output[1]=p;
        }

        if (a==1)
        {
            if (bxy>1 || bxy<0)
            {
                output[0] = "Regression of x on y does not exist";
            }
            else
            {
                output[0] = "x - "+avgx+" = "+bxy+" (y - "+avgy+")";
                float q=bxy*(var1-avgy)+avgx;
                p=Float.toString(q);
            }
            output[1]=p;
        }
        return output;
    }
    

    static float correlationcoefficient(float x[], float y[])
    {
        float[] ansset = varcalc(x,y);
        float stdx = (float)Math.sqrt(ansset[1]);
        float stdy = (float)Math.sqrt(ansset[2]);
        float covxy = ansset[0];
        float corrcoeff = covxy/(stdx*stdy);
        return (corrcoeff);
    }

    public static String[] multipleregression(float x[], float y[], float z[], int a, float var1, float var2)
    {
        float sumx = 0, sumy = 0, sumdevx = 0, sumdevy = 0, avgx = 0, avgy = 0, sumz = 0, avgz = 0, sumdevz = 0;
        int n = x.length;
        for (int i = 0; i<n; i++)
        {
            sumx += x[i];
            sumy += y[i];
            sumz += z[i];
        }

        avgx = sumx/(n);
        avgy = sumy/(n);
        avgz = sumz/n;

        float devx[];
        float devy[];
        float devz[];
        devx = new float[n];
        devy = new float[n];
        devz = new float[n];

        for (int i = 0; i<n; i++)
        {
            devx[i] = x[i]-avgx;
            devy[i] = y[i]-avgy;
            devz[i] = z[i]-avgz;
        }

        for (int j = 0; j<n; j++)
        {
            sumdevx += devx[j];
            sumdevy += devy[j];
            sumdevz += devz[j];
        }

        float devxsquare[];
        float devysquare[];
        float devzsquare[];

        devxsquare = new float[n];
        devysquare = new float[n];
        devzsquare = new float[n];

        for (int i = 0 ;i<n; i++)
        {
            devxsquare[i] = devx[i]*devx[i];
            devysquare[i] = devy[i]*devy[i];
            devzsquare[i] = devz[i]*devz[i];
        }

        float sumdevxsquare = 0, sumdevysquare = 0, sumdevzsquare = 0;

        for (int i = 0; i<n; i++)
        {
            sumdevxsquare += devxsquare[i];
            sumdevysquare += devysquare[i];
            sumdevzsquare += devzsquare[i];
        }

        float stdx = (float)Math.sqrt((sumdevxsquare/n)-((sumdevx/n)*(sumdevx/n)));
        float stdy = (float)Math.sqrt((sumdevysquare/n)-((sumdevy/n)*(sumdevy/n)));
        float stdz = (float)Math.sqrt((sumdevzsquare/n)-((sumdevz/n)*(sumdevz/n)));

        float r12 = correlationcoefficient(x,y);
        float r13 = correlationcoefficient(x,z);
        float r23 = correlationcoefficient(y,z);

        float b123 = ((stdx/stdy)*((r12 - (r13*r23))/(1-(r23*r23))));
        float b132 = ((stdx/stdz)*((r13 - (r12*r23))/(1-(r23*r23))));

        float b231 = ((stdy/stdz)*((r23 - (r12*r13))/(1-(r13*r13))));
        float b213 = ((stdy/stdx)*((r12 - (r23*r13))/(1-(r13*r13))));

        float b321 = ((stdz/stdy)*((r23 - (r13*r12))/(1-(r12*r12))));
        float b312 = ((stdz/stdx)*((r13 - (r23*r12))/(1-(r12*r12))));

        String ans[]= {"",""};
        String p="-";
        if (a == 1)
        {
            if (b123>1 || b132>1 || b123<0 || b132<0)
            {
                ans[0] = "Regression equation of x on y and z does not exist";
            }
            else
            {
                String x_on_yz = ("(x - "+avgx+") = "+b123+" (y - "+avgy+") + "+b132+"(z - "+avgz+")");
                ans[0] =  x_on_yz;
                float q=b123*(var1-avgy)+b132*(var2-avgz)+avgx;
                p=Float.toString(q);
            }
            ans[1]=p;
        }

        if (a == 2)
        {
            if (b213>1 || b231>1 || b213<0 || b231<0)
            {
                ans[0] = "Regression equation of y on x and z does not exist";
            }
            else
            {
                String y_on_xz = ("(y - "+avgy+") = "+b231+" (z - "+avgz+") + "+b213+"(x - "+avgx+")");
                ans[0] =  y_on_xz;
                float q=b231*(var2-avgz)+b213*(var1-avgx)+avgy;
                p=Float.toString(q);
            }
            ans[1]=p;
        }

        if (a==3)
        {
            if (b321>1 || b312>1 || b321<0 || b312<0)
            {
                ans[0]= "Regression equation of z on x and y does not exist";
            }
            else
            {
                String z_on_xy = ("(z - "+avgz+") = "+b312+" (x - "+avgx+") + "+b321+"(y - "+avgy+")");
                ans[0] =  z_on_xy;
                float q=b312*(var1-avgx)+b321*(var2-avgy)+avgz;
                p=Float.toString(q);
            }
            ans[1]=p;
        }
        return ans;
    }
    String[] data_two(String arr1, String arr2,int arr[],String var){
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
        int x=0;
        if(arr[2]==1){
            x=1;
        }
        else{
            x=2;
        }
        float var1=Float.parseFloat(var);
        String[] regression_equation_ans=regressionequation(num1,num2,x,var1);
        return regression_equation_ans;
    }
    String[] data_three(String arr1, String arr2, String arr3,int arr[],String var){
        String cval1= arr1.replaceAll(" ", "");
        String cval2= arr2.replaceAll(" ", "");
        String cval3= arr3.replaceAll(" ", "");
        String cval4= var.replaceAll(" ", "");
        String[] csval1 = cval1.split(",",cval1.length());
        String[] csval2 = cval2.split(",",cval2.length());
        String[] csval3 = cval3.split(",",cval3.length());
        String[] csval4 = cval4.split(",",cval4.length());
        int leng1 = csval1.length;
        int leng2 = csval2.length;
        int leng3 = csval3.length;
        int leng4 = csval4.length;
        float[] num1 = new float[leng1];
        float[] num2 = new float[leng2];
        float[] num3 = new float[leng3];
        float[] num4 = new float[leng4];
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
        i=0;
        for (String str:csval3){
            num3[i] = Float.parseFloat(str);
            i++;
        }
        i=0;
        for (String str:csval4){
            num4[i] = Float.parseFloat(str);
            i++;
        }
        float var1=num4[0];
        float var2=num4[1];
        int x=0;
        if(arr[2]==1){
            x=1;
        }
        else if(arr[3]==1){
            x=2;
        }
        else{
            x=3;
        }
        String[] regression_equation_ans=multipleregression(num1,num2,num3,x, var1,var2);
        return regression_equation_ans;
    }
    
}*/


public class model {

    public static float[] varcalc(float x[], float y[])
    {
        float sumx = 0, sumy = 0, sumdevx = 0, sumdevy = 0, avgx = 0, avgy = 0;
        int n = x.length;
        for (int i = 0; i<n; i++)
        {
            sumx += x[i];
            sumy += y[i];
        }

        avgx = sumx/(n);
        avgy = sumy/(n);

        float devx[];
        float devy[];
        devx = new float[n];
        devy = new float[n];

        for (int i = 0; i<n; i++)
        {
            devx[i] = x[i]-avgx;
            devy[i] = y[i]-avgy;

        }

        for (int j = 0; j<n; j++)
        {
            sumdevx += devx[j];
            sumdevy += devy[j];
        }

        float devxsquare[];
        float devysquare[];
        float devxy[];
        devxsquare = new float[n];
        devysquare = new float[n];
        devxy = new float[n];

        for (int i = 0 ;i<n; i++)
        {
            devxsquare[i] = devx[i]*devx[i];
            devysquare[i] = devy[i]*devy[i];
            devxy[i] = devx[i]*devy[i];
        }

        float sumdevxsquare = 0, sumdevysquare = 0, sumdevxy = 0;

        for (int i = 0; i<n; i++)
        {
            sumdevxsquare += devxsquare[i];
            sumdevysquare += devysquare[i];
            sumdevxy += devxy[i];
        }

        float covxy = (sumdevxy/n) - ((sumdevx/n)*(sumdevy/n));
        float varx = (sumdevxsquare/n)-((sumdevx/n)*(sumdevx/n));
        float vary = (sumdevysquare/n)-((sumdevy/n)*(sumdevy/n));


        float[] ans = new float[]{ covxy,varx,vary,avgx,avgy }; 
        return (ans);
    }

    public static String[] regressionequation (float x[], float y[], int a,float var1)
    {
        float ansset[] = varcalc(x,y);
        
        float covxy = ansset[0];
        float varx = ansset[1];
        float vary = ansset[2];
        float avgx = ansset[3];
        float avgy = ansset[4];

        float byx = covxy/varx;
        float bxy = covxy/vary;

        String output[]={"",""};
        String p="-";
        if (a == 2)
        {
            if (byx>1 || byx<0)
            {
                
                output[0] = "Regression of y on x does not exist";
            }
            else
            {
                output[0] = "y - "+avgy+" = "+byx+" (x - "+avgx+")";
                float q=byx*(var1-avgx)+avgy;
                p=Float.toString(q);
            }
            output[1]=p;
        }

        if (a==1)
        {
            if (bxy>1 || bxy<0)
            {
                output[0] = "Regression of x on y does not exist";
            }
            else
            {
                output[0] = "x - "+avgx+" = "+bxy+" (y - "+avgy+")";
                float q=bxy*(var1-avgy)+avgx;
                p=Float.toString(q);
            }
            output[1]=p;
        }
        return output;
    }
    

    static float correlationcoefficient(float x[], float y[])
    {
        float[] ansset = varcalc(x,y);
        float stdx = (float)Math.sqrt(ansset[1]);
        float stdy = (float)Math.sqrt(ansset[2]);
        float covxy = ansset[0];
        float corrcoeff = covxy/(stdx*stdy);
        return (corrcoeff);
    }

    public static String[] multipleregression(float x[], float y[], float z[], int a, float var1, float var2)
    {
        float sumx = 0, sumy = 0, sumdevx = 0, sumdevy = 0, avgx = 0, avgy = 0, sumz = 0, avgz = 0, sumdevz = 0;
        int n = x.length;
        for (int i = 0; i<n; i++)
        {
            sumx += x[i];
            sumy += y[i];
            sumz += z[i];
        }

        avgx = sumx/(n);
        avgy = sumy/(n);
        avgz = sumz/n;

        float devx[];
        float devy[];
        float devz[];
        devx = new float[n];
        devy = new float[n];
        devz = new float[n];

        for (int i = 0; i<n; i++)
        {
            devx[i] = x[i]-avgx;
            devy[i] = y[i]-avgy;
            devz[i] = z[i]-avgz;
        }

        for (int j = 0; j<n; j++)
        {
            sumdevx += devx[j];
            sumdevy += devy[j];
            sumdevz += devz[j];
        }

        float devxsquare[];
        float devysquare[];
        float devzsquare[];

        devxsquare = new float[n];
        devysquare = new float[n];
        devzsquare = new float[n];

        for (int i = 0 ;i<n; i++)
        {
            devxsquare[i] = devx[i]*devx[i];
            devysquare[i] = devy[i]*devy[i];
            devzsquare[i] = devz[i]*devz[i];
        }

        float sumdevxsquare = 0, sumdevysquare = 0, sumdevzsquare = 0;

        for (int i = 0; i<n; i++)
        {
            sumdevxsquare += devxsquare[i];
            sumdevysquare += devysquare[i];
            sumdevzsquare += devzsquare[i];
        }

        float stdx = (float)Math.sqrt((sumdevxsquare/n)-((sumdevx/n)*(sumdevx/n)));
        float stdy = (float)Math.sqrt((sumdevysquare/n)-((sumdevy/n)*(sumdevy/n)));
        float stdz = (float)Math.sqrt((sumdevzsquare/n)-((sumdevz/n)*(sumdevz/n)));

        float r12 = correlationcoefficient(x,y);
        float r13 = correlationcoefficient(x,z);
        float r23 = correlationcoefficient(y,z);

        float b123 = ((stdx/stdy)*((r12 - (r13*r23))/(1-(r23*r23))));
        float b132 = ((stdx/stdz)*((r13 - (r12*r23))/(1-(r23*r23))));

        float b231 = ((stdy/stdz)*((r23 - (r12*r13))/(1-(r13*r13))));
        float b213 = ((stdy/stdx)*((r12 - (r23*r13))/(1-(r13*r13))));

        float b321 = ((stdz/stdy)*((r23 - (r13*r12))/(1-(r12*r12))));
        float b312 = ((stdz/stdx)*((r13 - (r23*r12))/(1-(r12*r12))));

        String ans[]= {"",""};
        String p="-";
        if (a == 1)
        {
            if (b123>1 || b132>1 || b123<0 || b132<0)
            {
                ans[0] = "Regression equation of x on y and z does not exist";
            }
            else
            {
                String x_on_yz = ("(x - "+avgx+") = "+b123+" (y - "+avgy+") + "+b132+"(z - "+avgz+")");
                ans[0] =  x_on_yz;
                float q=b123*(var1-avgy)+b132*(var2-avgz)+avgx;
                p=Float.toString(q);
            }
            ans[1]=p;
        }

        if (a == 2)
        {
            if (b213>1 || b231>1 || b213<0 || b231<0)
            {
                ans[0] = "Regression equation of y on x and z does not exist";
            }
            else
            {
                String y_on_xz = ("(y - "+avgy+") = "+b231+" (z - "+avgz+") + "+b213+"(x - "+avgx+")");
                ans[0] =  y_on_xz;
                float q=b231*(var2-avgz)+b213*(var1-avgx)+avgy;
                p=Float.toString(q);
            }
            ans[1]=p;
        }

        if (a==3)
        {
            if (b321>1 || b312>1 || b321<0 || b312<0)
            {
                ans[0]= "Regression equation of z on x and y does not exist";
            }
            else
            {
                String z_on_xy = ("(z - "+avgz+") = "+b312+" (x - "+avgx+") + "+b321+"(y - "+avgy+")");
                ans[0] =  z_on_xy;
                float q=b312*(var1-avgx)+b321*(var2-avgy)+avgz;
                p=Float.toString(q);
            }
            ans[1]=p;
        }
        return ans;
    }
    String[] data_two(String arr1, String arr2,int arr[],String var){
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
        int x=0;
        if(arr[2]==1){
            x=1;
        }
        else{
            x=2;
        }
        float var1=Float.parseFloat(var);
        String[] regression_equation_ans=regressionequation(num1,num2,x,var1);
        return regression_equation_ans;
    }
    String[] data_three(String arr1, String arr2, String arr3,int arr[],String var){
        String cval1= arr1.replaceAll(" ", "");
        String cval2= arr2.replaceAll(" ", "");
        String cval3= arr3.replaceAll(" ", "");
        String cval4= var.replaceAll(" ", "");
        String[] csval1 = cval1.split(",",cval1.length());
        String[] csval2 = cval2.split(",",cval2.length());
        String[] csval3 = cval3.split(",",cval3.length());
        String[] csval4 = cval4.split(",",cval4.length());
        int leng1 = csval1.length;
        int leng2 = csval2.length;
        int leng3 = csval3.length;
        int leng4 = csval4.length;
        float[] num1 = new float[leng1];
        float[] num2 = new float[leng2];
        float[] num3 = new float[leng3];
        float[] num4 = new float[leng4];
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
        i=0;
        for (String str:csval3){
            num3[i] = Float.parseFloat(str);
            i++;
        }
        i=0;
        for (String str:csval4){
            num4[i] = Float.parseFloat(str);
            i++;
        }
        float var1=num4[0];
        float var2=num4[1];
        int x=0;
        if(arr[2]==1){
            x=1;
        }
        else if(arr[3]==1){
            x=2;
        }
        else{
            x=3;
        }
        String[] regression_equation_ans=multipleregression(num1,num2,num3,x, var1,var2);
        return regression_equation_ans;
    }
    
}


