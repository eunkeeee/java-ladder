# java-lines

사다리 타기 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

### 1단계 기능 목록

- [x] 사람 이름 입력

  - [x] 사람의 수는 2명 이상 100명 이하 `PersonNumber`
  - [x] 사람의 이름은 1글자 이상 5글자 이하 `Name`
  - [x] 빈 문자열(공백) 입력시 예외처리

- [x] 최대 사다리 높이 입력

  - 사다리 높이는 1 이상 100 이하 `Height`

- [x] 사람 이름 출력
- [x] 사다리 만들기
- [x] 사다리 출력

### 1단계 추가 기능 목록

- [x] 이름 앞뒤 공백 제거

### 1단계 궁금증...

- `Random`을 테스트 한다는 것은 무엇일까? 현재 코드에서는 `List<Boolean> points`에 연속적으로 `true`가 나올 수 없도록 코드를 작성했는데, 이런 경우에는 테스트 자체를 하기 어려운
  것일까?
- `Map`을 사용해 `Controller`를 정리할 때에, 필드에 변수가 너무 많아지는 문제점이 발생한다. 어떻게 하면 개선할 수 있을까?

  - 내가 생각한 해법

  1. `GameProperty` 클래스를 새로 만든 뒤, 필드에 `names`, `lines`와 같은 클래스들을 넣어서 관리한다.
  2. `ApplicationStatus`를 너무 촘촘하게 작성하지 말고, `SET_GAME`, `CREATE_LINES`, `APPLICATION_EXIT` 이런 식으로 퉁 쳐서 필드를 좀 줄여본다.

- `util` 패키지의 `BooleanGenerator`는 테스트를 용이하게 하기 위해서 만들었지만, 현재 코드에서는 `TrueBooleanGenerator`를 활용하지 못했다. 어떤 식으로 테스트 코드, 프로덕션
  코드를 작성한다면 저 클래스를 사용할 수 있을까...?

- TDD 방식을 사용할 때에 `points`를 랜덤으로 생성하는 과정에 대한 테스트 input, output 조차도 생각해내기 어려웠다. 이럴 때에는 잠시 TDD를 멈춰도 될까...?

- 처음에는 `util`에 `Validation` 클래스들을 만들어서 각 입력값에 대한 검증을 진행하고, `InputView`에서 모든 검증을 마친 후에 `domain`으로 검증된 값만을 가져왔다. 이
  때에, `Validation` 클래스는 `static` 메서드를 사용해서 작성하는 것이 좋을까? 인스턴스를 만들어서 사용한다 하면 검증 클래스의 상태가 크게 변하는 일은 드무니 싱글턴 패턴 등을 사용해 검증
  클래스의 인스턴스 무제한 생성을 막는 것이 좋을까? 검증 클래스에 대한 인스턴스를 생성하는 것 자체가 어색하게 느껴지는데, `static` 클래스를 사용하는게 좋을까?

- 검증에 대해서, 후에 리팩터링 과정에서 `InputView`에서는 최대한 적은 검증을 하고, 값을 바로 `domain`으로 데려와서 원시값을 포장하며 동시에 검증과 예외 처리를 했다. 어떤 방식이 더 좋을까?
- 아니면 `View`에서 값을 받을 때를 프론트엔드에서 사용자에게 표시한다 생각하고, `domain`에서의 검증을 데이터베이스에 접근하기 직전이라 생각하면 두 번 검증하는 방법이 필요한 경우도 있지 않을까?

  - 내가 생각한 임시 대답
  - 성능 안정성 중 더 우선시 되는 것에 따라서 다를 것 같다. 비밀번호 등등 비즈니스에 중요한 로직이고, 잘못된 값이 데이터베이스에 입력되었을 때 큰 에러를 일으킬 소지가 있다면 잘 저울질해서 사용하는게
    맞을 것 같다.

    예를 들어, 게임에서 닉네임을 사용한다 했을 때, 이전 사용자들과 중복되는 닉네임을 사용할 수 없다고 한다면!
    만약에 사용자가 중복되는 닉네임을 입력했을 때, 한 번 중복되는 닉네임이 있습니다 다시 입력해 주세요라고 표시한다면
    사용자가 더 빠르게 이 닉네임을 사용할 수 없구나... 해서 알려져서 좋을 것 같고!
    혹시나 사용자가 html(프론트엔드에서 입력받는다면)을 조작하거나, view를 조작해서, 또는 버그가 나서 view에서 표시를 못하게 되어 잘못된 정보를 입력하더라도 비싼 DB에 이상한 것이 섞여 들어가면
    후에 어느 값이 잘못 된건지 찾기도 어렵고, 복구가 어려울 수 있으니 두 번 하는 것도 때로는 가치가 있을 것 같다

  - 하지만 두 번 검증하게 되면, 코드에 중복되는 내용이 발생할 수밖에 없으니, 검증 내용이 변경되는 경우 등에 유연하게 대처하기 어려워질 수 있어서 유지 보수에 어려움이 존재할 수 있을 것 같다.

### 리팩터링 목록

- [ ] `booleanGenerator`가 `utils` 클래스에 있는게 적절할까?
      <<<<<<< HEAD
- [x] `booleanGenerator`를 현재 `Line`에서 생성해 사용하는 것이 적절할까? 주입받는다면 어디부터 받아야할까? => `Application`부터 주입시켰다!
- [ ] `Controller`에 필드가 많은 것에 대해 어떻게 해결해야 할까? => 일단 없애긴 했는데, 메서드를 분리하다보면 계속 필드가 발생한다... 어떻게 해결할 수 있을까..?
- [ ] 현재 사용하는 MVC 패턴의 `Controller`의 모습은 괜찮은 걸까? => 일단 `map`을 통해서 `gameGuide`를 받는 방식은, 다른 사람이 하는 것이 멋져 보여서 따라한 건데 내가 뚜렷한
      장점에 대해 설명할 수 없으므로 적용하지 않기로 했다.
- [x] `point`의 `true`, `false`도 `EXISTS`, `NONE`으로 포장할 수 있지 않을까?
- [x] `Names` 객체를 `view`에 보낼 때 안전장치 추가

---

### 2단계 기능 목록

- [x] 미션을 입력받는다.

  - [x] 미션의 글자수가 5글자 이상이면 예외 처리한다.
  - [x] 미션이 공백인 경우 예외 처리한다.

- [x] 결과를 보고 싶은 사람을 입력받는다.

  - [x] 입력값에 해당하는 사람이 없으면, 예외 처리한다.
  - [x] 입력값에 해당하는 사람이 `all`이면 전체 결과로 인식한다.

- [x] 결과를 출력한다.

- [x] 같은 닉네임의 사용자를 입력하면 예외 처리한다.
- [ ] `Position` 객체의 활용

---

### 2단계 리팩터링 궁금증

- `Names`에서 받은 이름의 개수가 4개라면, `Missions`(당첨, 꽝 등)를 통해서도 오직 4개만의 미션을 받아야 한다. 이 검증은 어떤 클래스에서 하는 것이 가장 적합할까? 나중에 생성되는
  객체인 `Missions` 객체에서 하게 된다면, 외부에서 총 개수를 주입받는 것은 테스트 코드에서도, 사람의 수를 입력받는다는 점에서 객체 관점에서도 약간 어색하게 느껴진다.

  - 일단 `MainController`에서 검증하게 만들기는 했으나, 굉장히 어색하게 느껴진다. => 결렬
  - 리팩터링을 통해 늦게 생성하는 `Mission`의 **생성자에 `int size`를 파라미터로 추가**했다. 여전히, 어색하게 느껴진다.
  - 한 크루(`말랑`)는 이 검증 과정에서 예외가 발생한다면, `사람의 수와 미션의 수가 다릅니다`라고 출력할 것이기 때문에, `Names`를 파라미터로 그대로 넘겨줬다고 하는데, 나는 이 방법은 더
    이상하다고 느꼈다.

- 원시값 포장은 얼만큼 하는 것이 좋을까? 현재 `Player`가 각각 가져야 하는 이름, 위치, 미션을 모두 포장했는데, 적절한 방법일까?

  - 원시값 포장을 하다 보면 일급 컬렉션을 많이 생성하게 된다. `Name`을 포장하니 `Names`가 필요했고, `Mission`을 포장하니 `Missions`가 필요했다.
  - 일급 컬렉션을 꼭 생성해야 하는 경우와, 불필요한 경우를 어떻게 설계 과정에서 구분할 수 있을까?
  - 원시값을 포장하다 보니, `equals()`와 `hashCode`를 오버라이딩하는 경우가 지나치게 많아졌다. 괜찮을까?

- 어떤 객체가 어떤 일을 해야 하는지 불분명하다.
  - 현재 `Controller`에서는 `view`와 관련된 작업 + `LadderMaker`, `LadderGame`을 작동하는 작업 이외에는 모두 제거하였다. 내가 생각하는 `Controller`는 어떤
    일을 하는 것인지 아직 모호하다.
- `Player`가 능동적으로 움직인다는 의미를 주고 싶어서 작성한 메서드(아래)에 `Lines`를 파라미터로 전달해야 한다.
  - 도메인의 두 클래스가 굉장히 서로 연관이 크게 되어 있는데, 개선할 수 있는 방법을 모르겠다.
  ```
  public void move(Lines lines) {
      this.position = new Position(lines.getExitIndex(this.position.getPosition()));
  }
  ```
- 객체의 생성과 동시에 객체의 상태를 모두 정의하고 싶은데, 객체 생성 이후에 상태를 추가해야 하는 경우에 응집도가 떨어진 객체가 만들어지는 것 같다.
  - # 처음에는 `Player` 객체에 `name, position, mission(최종결과)`를 모두 상태값으로 지정했다. - 하지만 이 방법을 사용하자 `Player`의 초기화 시점에서는 최종 결과를 업데이트할 수 없었다. - 내가 생각한 해결책 1. `Result` 클래스를 별도로 작성해 `Map<Player, Mission>` 형태로 전체의 결과를 조회하는 방법 => 현재 선택함 - 장점 - `Player` 객체가 생성되는 시점에 이미 완전하다. - 이후에 별도의 setter를 사용하지 않아도 된다. - 단점 - `Player` 객체에 최종 미션이 존재하지 않아서 완전한 정보가 담기지 못한다. - 사용자 이름에 따른 조회를 `Players`에서 검색하는 형태로 하고 싶은데, `ladderGame`에서 검색하는 형태가 되어버렸다. 2. 별도의 setter(`distributeMission`)를 통해서 `Player`에 `mission` 필드를 만드는 것 중에 어떤 것이 더 좋을까? - 장점 - `Player`객체의 관점에서 완전한 정보가 담겼다. - 조회 시에 `Players` 객체에서 `Player`의 이름을 전달해 결과를 조회할 수 있다. (내 생각에 자연스럽다!) - 단점 - `Player` 객체의 생성 시에 필드를 모두 업데이트하지 않기 때문에 다른 개발자에게 혼란을 유발할 수 있다.
- [ ] `booleanGenerator`를 현재 `Line`에서 생성해 사용하는 것이 적절할까? 주입받는다면 어디부터 받아야할까?
- [ ] `Controller`에 필드가 많은 것에 대해 어떻게 해결해야 할까?
- [ ] 현재 사용하는 MVC 패턴의 `Controller`의 모습은 괜찮은 걸까?
  > > > > > > > 9cc2eaa (docs: 리팩터링 목록 작성)
