# IndexActivity.java
```java
Handler handler = new Handler();
```
핸들러 객체를 생성. 핸들러를 통해 일정 시간 뒤에 우리가 원하는 작업을 수행할 수 있음.           

```java
handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goMainActivity();
            }
        },1500);
```
postDelayed() 메소드는 우리가 원하는 작업을 일정 시간 뒤에 실행하도록 만들 수 있음.         
단, 반드시 Runnable 객체를 인자로 지정해야 하므로 run() 메소드에 코드를 작성해야함.         

# MemoItem.java
```java
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
```
날짜를 표시하기 위한 포매터     

```java
Date date = new Date();
```
현재 날짜 정보를 가진 Date 객체 생성    

```java
String regDate = formatter.format(date);
```
생성된 포매터를 통해 현재 날짜 객체를 지정해서 새로운 format에 맞는 날짜 정보는 regDate에 저장.     

# MemoListAdapter.java
```java
notifyDataSetChanged();
```
notifyDataSetChanged() 메소드를 호출해서 추가된 아이템 리스트를 리사이클러뷰에 반영.        
notifyDataSetChanged() 메소드를 호출하지 않을 경우 추가된 아이템이 보이지않음.      

