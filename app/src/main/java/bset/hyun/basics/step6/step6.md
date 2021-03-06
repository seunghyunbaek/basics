# 데이터베이스와 SQL

데이터베이스는 데이터하고 베이스를 나눠보면 데이터가 저장된 저장소를 가리키는게 데이터베이스입니다. 베이스가 밑에 기반을 다져준다는 얘기가 됩니다.  
데이터를 한군데 모아서 저장해두고 필요하면 가져갈 수 있도록 만드는게 데이터베이스라고 할 수 있습니다.  

데이터베이스의 구조를 살펴보겠습니다.  
저장소 안에 여러 개의 테이블이 들어가 있는 게 관계형 데이터베이스의 가장 기초적인 구조가 됩니다. 이 테이블이라고 하는게 데이터를 담아놓는 작은 통이 됩니다. 

데이터베이스는 흔히 네 단계를 잘 기억을 하면됩니다.  
네 단계는 처음에 이 저장소를 만들거나 저장소에 접근하기 위해서 오픈을 하는 단계가 첫 번째 단계입니다.  
두 번째 단계는 이 테이블이라고 하는 게 하나의 단위니까 이 테이블이라고 하는 거의 구조를 만드는 겁니다. 테이블을 만드는 단계입니다.  
세 번째는 이 테이블의 구조에 맞게 데이터를 집어넣는 단계입니다.  
네 번째는 데이터를 조회하는 단계입니다.  
조회라고하면 보통 이제 쿼리라고도 부르는데, 일단 규칙에 맞게 데이터를 꺼내서 보는 걸 얘기합니다.  
네 단계를 꼭 기억해야 됩니다. 왜냐하면 이거에 따라서 코드에 어느 부분에 어떤게 들어간다고 하는게 정리가 될 것이기 때문입니다.  


테이블을 조금 더 살펴보겠습니다.  
테이블은 릴레이션이라고 부릅니다. 그래서 관계형 데이터베이스, 릴레이셔널 데이터베이스라고 부릅니다. 우리가 흔히 테이블이라고 부르는 거를 학문적으로 릴레이션이라고 부르는 겁니다. 
그래서 관계형 데이터베이스는 테이블이라고 하는 것을 쓰고 격자 형태로 되어 있으니까 엑셀에서 스프레드시트 한 장을 보는 것처럼 일목요연하게 볼 수 있습니다. 그래서 줄을 보통 레코드, 투플이라고 부릅니다. 
릴레이션을 테이블이라고 부르는 것과 마찬가지입니다.  
그리고 테이블의 구조를 정의할 떄는 각각의 칼럼의 이름과 타입을 정의하고 그걸 스키마라고 부릅니다.  
  
  
테이블을 정의하는 단계가 끝나면 이제 SQL문을 사용합니다. SQL문만 공부하는데도 몇 개월 걸릴 수도 있습니다. 근데 우리가 앱이나 이런데서 사용하는 SQL문이 그렇게 많지 않습니다. 그래서 복잡한 SQL문을 잘 쓰질 않습니다. 
왜냐하면 앱의 단말에 저장할 데이터는 보통 간단한 형식으로 저장을 합니다. 그래서 그렇게 저장을 하려고 생각을 하니까 SQL문도 복잡한 걸 원하지 않습니다.  
SQL문을 작성할 때에 띄어쓰기, 콤마 등을 주의해야할 부분입니다.  
  
  
# SQL 문  g
테이블 생성  
CREATE TABLE [IF NOT EXISTS] table_name(col_name col_definition, ...)  
  
레코드, 데이터 추가  
INSERT INTO table_name<(column list)> VALUES (value, ...)  
  
레코드, 데이터 조회
SELECT col_name, ... FROM table_name [WHERE ...]


# SQLiteOpenHelper
데이터베이스를 만들거나 열기 위해 필요한 일들을 도와주는 역할을 합니다.  
기존에 설치되어있는 데이터베이스가 있는지 그 데이터베이스의 버전이 뭔지를 확인하고, 버전이 바뀌면 우리가 거기에 따라서 별도로 데이터를 삭제하지 않도록 만들 수 있습니다. 
OpenHelper라고 하는거는 상속을 해서 만들면 onCreate(), onOpen(), onUpgrade()를 재정의할 수 있습니다. 이 메서드는 언제 호출이 될까요?  
  
만약에 어떤 사용자한테 이 앱을 배포를 했을 때 데이터베이스를 오픈했는데 만들어진 데이터베이스가 없다고 하면 onCreate가 호출됩니다.  
이미 사용하고 있는 데이터베이스가 있으면 onOpen()이 호출이 됩니다. 그리고 그 버전이라든가 이런 거 하고 상관없이 근야 오픈해주는 메서드도 사용을 할 수가 있게 됩니다.  
만약에 기존에 쓰고 있는 사용자면 onUpgrade() 안에다가 어떻게 데이터베이스를 변경하면 된다고 하는 코드를 넣어주면 그게 실행이 됩니다. 그래서 버전에 따라서 다른 코드를 넣어줄 수 있는 장점이 생깁니다.  
  
  
# 앱에서 어떻게 쓰일 수 있을까?
앱에서 쓰는 경우를 생각해보면 만약에 프로필을 저장한다고 하면 저장화면이 있고 조회 화면이 있을겁니다. 
그래서 저장화며에서는 INSERT 문을 사용하면 되고, 조회 화면에서는 SELECT 문을 써서 가져온 다음에 리스트 뷰 같은데다가 넣어서 보관해주면 됩니다. 
리스트 뷰의 어디다 놔야 될까요? 어댑터에 놓으면 되겠죠. 어뎁터에다가 이 데이터를 저장할 수 있는 구조를 만들어서 넣고 뷰를 생성해서 리턴해주면 되는 겁니다.  
  
그럼 데이터베이스 오픈이라던가 테이블 만들기는 어디다 넣어줄까요?  
앱이 처음에 생성되거나 앱이 처음에 실행돼서 onCreate가 실행되거나 초기화하는 단계에서 얘를 코드상에서 자동으로 해주면 됩니다.  
insert나 select는 사용자가 뭔가 이벤트를 발생시켰을 때 처리하면 되고, 나머지는 미리 실행되도록 만든다는 겁니다.  
  
그리고 또 한가지는 이게 액티비티라는 화면에서 이렇게 실행되는 경우가 있고, 이렇게 하면 액티비티가 없어지면 데이터베이스 객체도 참조할 수가 없습니다. 
그래서 이걸 서비스에서 해주는 경우가 있습니다.  
그래서 서비스에서 해주게 되면 우리가 해봤던 것처럼 액티비티에서 서비스로 이 데이터를 날려주고 서비스에서 저장을 하고 
그 다음에 조회하는 경우에도 서비스에서 조회한 걸 다시 액티비티로 보내서 화면에 표시를 해주게 됩니다. 
