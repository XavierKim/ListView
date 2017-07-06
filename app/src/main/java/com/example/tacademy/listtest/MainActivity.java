package com.example.tacademy.listtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tacademy.listtest.model.DaumSearchResultModel;
import com.example.tacademy.listtest.ui.GridActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 화면 전환
        //Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
        Intent intent = new Intent(MainActivity.this, GridActivity.class);
        startActivity(intent);
        finish();


        // Json Test
        // Json은 플랫폼과 언어에 독립적인 데이터 포멧(텍스트타입)
        // 키:값 구성
        // 여러개면 , 로 구분한다
        // 값의 자리에는 또다른 json, 값, 배열
        // Json 하나는 class 1개가 대비된다.
        String src1  = "{\"name\":\"kkk\", \"age\":10}";
        // 파싱해서 이름과 나이를 출력하시오
        Gson gson = new Gson();
        // 파싱
        Model1 m1 = gson.fromJson(src1, Model1.class);
        Log.i("T", m1.getName()+" / "+ m1.getAge());

        // 실습 아래 src2를 파싱하여 이름과 나이를 출력하세요
        String src2  = "{ \"persion\":{\"name\":\"kkk\", \"age\":10}}";
        Model2 m2 = gson.fromJson(src2, Model2.class);
        Log.i("T", m2.getPersion().getName()+" / "+ m2.getPersion().getAge());

        // 실습 아래 src3를 파싱하여 이름과 나이를 출력하세요
        String src3 = "{ \"persion\":[{\"name\":\"kkk\", \"age\":10}, {\"name\":\"kkk2\", \"age\":20}]}";
        Model3 m3 = gson.fromJson(src3, Model3.class);
        for( Model1 mo : m3.getPersion()){
            Log.i("T", mo.getName()+" / "+ mo.getAge());
        }

        // 복잡한 구조
        String src4 = "{\"channel\":{\"result\":\"10\",\"pageCount\":\"3925\",\"title\":\"Search Daum Open API\",\"totalCount\":\"59134\",\"description\":\"Daum Open API search result\",\"item\":[{\"pubDate\":\"20141001120900\",\"title\":\"&lt;b&gt;다음카카오&lt;/b&gt; 출범, 새로운 공룡기업 탄생으로 IT업계 지각변동 예고\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/Bc34GvWMWYA\",\"cp\":\"728111\",\"height\":\"321\",\"link\":\"http://blog.naver.com/greengtec/220138021434\",\"width\":\"640\",\"image\":\"http://postfiles5.naver.net/20141001_68/greengtec_1412131149750xW2A9_JPEG/%B4%D9%C0%BD%C4%AB%C4%AB%BF%C0_%C3%E2%B9%FC__%285%29.jpg?type=w2\",\"cpname\":\"네이버블로그\"},{\"pubDate\":\"20141113070000\",\"title\":\"&lt;b&gt;다음카카오&lt;/b&gt;, 뱅카 불안하다\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/DekctEB6u3G\",\"cp\":\"794570\",\"height\":\"620\",\"link\":\"http://handay23.tistory.com/97\",\"width\":\"923\",\"image\":\"http://cfile25.uf.tistory.com/image/224C5F35546338B42B7A69\",\"cpname\":\"티스토리\"},{\"pubDate\":\"20141001123000\",\"title\":\"&lt;b&gt;다음카카오&lt;/b&gt; 관전포인트 2가지\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/r3xDAIjp1s\",\"cp\":\"728111\",\"height\":\"361\",\"link\":\"http://blog.naver.com/hyunjumall/220138005816\",\"width\":\"740\",\"image\":\"http://postfiles16.naver.net/20141001_63/hyunjumall_1412131644973paAkl_PNG/%B4%D9%C0%BD%C4%AB%C4%AB%BF%C0.PNG?type=w2\",\"cpname\":\"네이버블로그\"},{\"pubDate\":\"20140526162304\",\"title\":\"&lt;b&gt;다음카카오&lt;/b&gt; 출범, &#39;IT-모바일 시너지 극대화&#39;\",\"thumbnail\":\"https://search4.kakaocdn.net/argon/130x130_85_c/AJx4UUI5bH1\",\"cp\":\"16hbksq-KQLf9-Qa4k\",\"height\":\"318\",\"link\":\"http://www.smedaily.co.kr/news/articleView.html?idxno=47678\",\"width\":\"480\",\"image\":\"http://www.smedaily.co.kr/news/photo/201405/47678_14198_247.jpg\",\"cpname\":\"중소기업신문\"},{\"pubDate\":\"20140930180455\",\"title\":\"&lt;b&gt;다음카카오&lt;/b&gt;... 네이버와 한판..?? 과연..?\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/7FSo2cbHTkv\",\"cp\":\"269393\",\"height\":\"262\",\"link\":\"http://blog.daum.net/pmon119/59\",\"width\":\"500\",\"image\":\"http://cfile228.uf.daum.net/image/255CB83D542A716C326644\",\"cpname\":\"Daum블로그\"},{\"pubDate\":\"20141001230100\",\"title\":\"&lt;b&gt;다음카카오&lt;/b&gt; 네이버에대한 도전일까?\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/KymZdvcEnqa\",\"cp\":\"728111\",\"height\":\"252\",\"link\":\"http://blog.naver.com/btskin/220138651105\",\"width\":\"550\",\"image\":\"http://postfiles9.naver.net/20141001_232/btskin_1412171276728mfNO1_JPEG/%B4%D9%C0%BD%C4%AB%C4%AB%BF%C0_%281%29.jpg?type=w2\",\"cpname\":\"네이버블로그\"},{\"pubDate\":\"20140828090500\",\"title\":\"&lt;b&gt;다음 카카오&lt;/b&gt; 합병 승인\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/BTRyEsrCqlT\",\"cp\":\"728111\",\"height\":\"288\",\"link\":\"http://blog.naver.com/riverpul2000/220105870765\",\"width\":\"328\",\"image\":\"http://postfiles16.naver.net/20140828_143/riverpul2000_1409184097952t2IJR_JPEG/%B4%D9%C0%BD%C4%AB%C4%AB%BF%C0%C7%D5%BA%B4.jpg?type=w3\",\"cpname\":\"네이버블로그\"},{\"pubDate\":\"20150902060514\",\"title\":\"&#39;&lt;b&gt;다음카카오&lt;/b&gt;→&lt;b&gt;카카오&lt;/b&gt;&#39;..&#39;&lt;b&gt;daum&lt;/b&gt;&#39;은 역사 속으로 사라지나?\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/4UIu2F42P6X\",\"cp\":\"16nfco03BTHhdjCcTS\",\"height\":\"300\",\"link\":\"http://v.media.daum.net/v/20150902060514364\",\"width\":\"300\",\"image\":\"http://t1.daumcdn.net/news/201509/01/joongang/20150901143114515ehvz.jpg\",\"cpname\":\"중앙일보\"},{\"pubDate\":\"20141001120900\",\"title\":\"&lt;b&gt;다음카카오&lt;/b&gt; 출범, 새로운 공룡기업 탄생으로 IT업계 지각변동 예고\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/AYOQx74570X\",\"cp\":\"728111\",\"height\":\"276\",\"link\":\"http://blog.naver.com/greengtec/220138021434\",\"width\":\"650\",\"image\":\"http://postfiles9.naver.net/20141001_168/greengtec_1412130768703bbKQv_JPEG/%B4%D9%C0%BD%C4%AB%C4%AB%BF%C0_%C3%E2%B9%FC__%281%29.jpg?type=w2\",\"cpname\":\"네이버블로그\"},{\"pubDate\":\"20150716124300\",\"title\":\"네이버 포스트와 &lt;b&gt;다음카카오&lt;/b&gt; 브런치에 대한 조금 이른 걱정\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/IcMdHFo9XYi\",\"cp\":\"728111\",\"height\":\"183\",\"link\":\"http://tomiopic.blog.me/220421821504\",\"width\":\"320\",\"image\":\"http://postfiles4.naver.net/20150716_179/tomiopic_1437017780342zPKfz_JPEG/%B4%D9%C0%BD%C4%AB%C4%AB%BF%C0_%BA%EA%B7%B1%C4%A1.jpg?type=w2\",\"cpname\":\"네이버블로그\"}],\"lastBuildDate\":\"Wed, 05 Jul 2017 09:15:11 +0900\",\"link\":\"http://dna.daum.net/apis\",\"generator\":\"Daum Open API\"}}";
        DaumSearchResultModel m4 = gson.fromJson(src4, DaumSearchResultModel.class);
        for( DaumSearchResultModel.Channel.Item item : m4.getChannel().getItem()){
            Log.i("T", item.getTitle());
        }

    }

    class Model3{
        ArrayList<Model1> persion;
        public ArrayList<Model1> getPersion() {
            return persion;
        }
        public void setPersion(ArrayList<Model1> persion) {
            this.persion = persion;
        }
    }
    class Model2{
        Model1 persion;
        public Model1 getPersion() {
            return persion;
        }
        public void setPersion(Model1 persion) {
            this.persion = persion;
        }
    }
    class Model1{
        String name;
        int age;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
    }
}















