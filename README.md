###### tags: `類神經網路作業`
# Multilayerperceptrons

1. 程式執行說明:
   執行檔**multilayerperceptrons.jar**要用命令題示字元開啟，並且要先下載JavaFX:
>    java --module-path "C:\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml -jar multilayerperceptrons.jar
   
2. 執行介面說明:

   ![](https://i.imgur.com/IPlXyG1.png)

    * 左方:
        * Hidden layer 可輸入隱藏層數(若輸入為小於1則自動設為1)。
        * Neurons number 可輸入每層隱藏層的神經元數(若輸入為小於1則自動設為1)。
        * 可選擇檔案，根據所選的檔案，File Name和Dimension 會跟著該檔案的名稱與維度更動。
        * 按下Start則會根據所選的各種條件進行運算。

    * 上方可調整學習率(0 ~ 1，以0.1為單位)與收斂條件(學習次數:200~3500，以50為單位)。
    * 結果會以二維的方式顯示於中間座標中。若為大於二維，則取前兩個維度會至於座標中。
    * 右方會顯示訓練結果 (各神經元之鍵結值、訓練辨識率、測試辨識率、均方根誤差)


3. 執行結果說明:

   ![](https://i.imgur.com/QqX92c7.png)
    * 座標上的點:
        * 點的顏色深淺:
          以顏色深淺區分訓練及測試資料。淺色為訓練資料，深色為測試資料。
        * 點的顏色色系:
          以不同顏色色系表示不同種類資料。藍色為第一類資料，綠色為第二類資料，橘色為第三類資料，紫色為第四類資料，紅色為第五類資料，其他類資料皆為藍綠色(沒有限制可以區分的資料種類數，但是在圖上最多只能表示6個種類)。
        * 舉例:
          深藍為期望輸出為第一類的測試資料；淺藍為期望輸出為第一類的訓練資料；深綠為期望輸出為第二類的測試資料；淺綠為期望輸出為第二類的訓練資料，以此類推。
    * 以紅色邊的點表示錯誤分類，以藍灰色的邊表示正確分類。
    * 座標上有標示1單位，可依此參考各點座標值。
    * 鍵結值的表示(依照所設的隱藏層數與每層的神經元數繪製出下圖):

      ![](https://i.imgur.com/V2fRF6J.png)

      鍵結值的表示法以上圖W來表示。以W0為例，W0=(W0[0],W0[1],W0[2])。


4. 程式碼簡介:
   程式用Java編寫，使用JavaFX與scene builder的GUI完成最後結果。 使用多層感知機來實作，且可以調整隱藏層數與隱藏層神經元數，輸出層為一個神經元，根據最後輸出判斷分成哪一類(以兩類的資料為例，輸出介於 0 ~ 0.5 分為第一類，介於 0.5 ~ 1 分為第二類)。
    * 程式碼分為兩個檔案
        * multilayerperceptrons: 為主程式檔，讀取fxml檔並建立GUI。
        * MainController: 為控制介面的檔案，大部分的運算與操作也在此設定。以下程式碼說明以此檔為主。	
    * 以function分類
    	* **Initialize()**: 初始可選擇的檔案。	
    	* **onSliderChanged_learning_rate()**: 調整學習率，並進行運算顯示結果(Show_point())。
        * **onSliderChanged_learning_times()**: 調整學習次數，並進行運算顯示結果(Show_point())。
        * **_Start_calculate()**: 按下Start按鍵，存取輸入的隱藏層數與每層的神經元數，並進行運算顯示結果(Show_point())。
        * **File_Choice()**: 將檔案名稱改為所選擇的檔案，並設choose_file為所選擇的檔案，再進行運算顯示結果(Show_point())。
        * **Show_point()**:
            * 進行運算(calculate())。
            * 顯示出運算結果(鍵結值、訓練辨識率、測試辨識率)
            * 畫出座標軸
            * 根據點的座標經過轉換後畫在座標上(轉換目的為將所有點都印在座標上，不會有超出邊界的點。新座標x’=600+原座標x *250/X_Y_max，其中600為座標原點的x座標，250為可容納的最大x座標，X_Y_max為所有原作標x與y中，有出線的最大值。同理新座標y’=400-原座標y *250/X_Y_max，其中400為座標原點的y座標，又因為javafx的座標為越下面越大，因此用”-“的。)
            * 設定點的半徑(點越多半徑越小)與顏色(代表不同類別資料及訓練或測試資料)。
            * 畫出單位座標(利用與畫作標點相似的方法)。
            * 畫出鍵結值向量。
        * **calculate()**:
            * 根據所選擇的檔案讀檔。
            * 初始鍵結值(w=(-1,0~1 random,0~1 random))。
            * 將讀到的每一筆資料存起來，並記錄有出現的期望輸出值。
            * 讓每一筆資料隨機排列(Ran_list())。
            * 判斷有幾筆訓練資料(全部資料的2/3)，幾筆測試資料(全部資料的1/3)。
            *  初始鍵結值(initial_w())。
            *  進行訓練:
            ```
            //前饋
            for(j=0;j<Neu_numbers;j++)
                if(j<Neurons)//第一層
                    v=∑(W [j] *data_x);
                else//不是第一層
                        v= ∑(W [j] *y);//the layer calculate by the before output	
                 y=y_function(v);

            //倒傳遞
            for(j=Neu_numbers-1;j>=0;j--) //從後面看回來
                if(j==Neu_numbers-1)//輸出層
                    Delta[j]= (desire_output-y)*y*(1-y);
                else if(j/Neurons==hidden_layer-1)//最後一層隱藏層					
                Delta[j]=y*(1-y)*(Delta[Neu_numbers-1]*W);
                else //其他隱藏層
                    Delta_x_W=Delta_x_W+Delta*W;
                    Delta[j]=y*(1-y)*Delta_x_W;
            //調整鍵結值
            for(j=0;j<Neu_numbers;j++)
                if(j/Neurons==0) //第一層隱藏層
                    W [j] =W [j] +Learningrate*Delta[j]* data_x;
                Else//其他隱藏層
                    W [j] =W [j]+Learningrate*Delta[j]*y;
            ```
            *  根據訓練的結果進行測試。
            * 計算訓練與測試辨識率與均方根誤差。
	    * **initial_w(int)**:隨機初始鍵結值。
        * **v_function(int,int)**:神經元內部激發狀態。
        * **y_function(double)**:活化函數，得到類神經元的輸出。
        * **correct(int,int)**:計算資料的辨識率。
        * **Ran_list()**:打亂原本讀入資料的順序。
        * **RMSE()**:計算均方誤差。
        * **output(double)**:回傳實際輸出，也就是最後分成的類別。



