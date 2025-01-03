# [Platinum II] 물채우기 - 17621 

[문제 링크](https://www.acmicpc.net/problem/17621) 

### 성능 요약

메모리: 16948 KB, 시간: 160 ms

### 분류

자료 구조, 구현

### 제출 일자

2024년 10월 23일 23:49:28

### 문제 설명

<p>N×M크기의 격자칸이 있다. 가장 왼쪽 위 칸을 (1, 1), 가장 오른쪽 아래 칸을 (N, M)이라고 하자. 격자칸의 제일 아래 행 바로 밑에는 물이 투과하지 못하는 <strong>바닥</strong>이 있다고 한다.</p>

<p>격자칸의 각 칸들은 막혀 있거나, 뚫려 있다. 막혀 있는 칸은 물이 투과하지 못한다. 한 열을 보면 막혀 있는 칸이 없거나, 막혀있는 칸들이 하나의 <strong>덩어리</strong>로 연속 하여 나타난다. 인접한 막혀 있는 칸들 사이로 물이 투과하지 못한다. 한 열에 있는 덩어리가 가장 아래 행에 있는 칸을 포함할 때 이 덩어리를 <strong>바닥에 붙은</strong> 덩어리라고 부른다. 바닥에 붙은 덩어리가 아닌 덩어리는 <strong>공중에 뜬</strong> 덩어리라고 부른다.</p>

<p>인접한 열에 있는 두 덩어리를 보았을 때, 두 덩어리가 같은 행에 있는 칸을 포함하면 두 덩어리는 붙어서 한 덩어리가 된다. 이렇게 붙은 세로 경계로도 물은 투과하지 못한다. 이렇게 붙는 과정은 연속한 열에 모두 적용된다. 아래 그림의 두 번째와 세 번째 열처럼 바닥에 붙은 덩어리와 공중에 뜬 덩어리가 붙어야 하는 경우는 <strong>주어지지 않는다</strong>. 즉, 인접한 열에 있는 덩어리들이 붙는 경우는 둘 다 바닥에 붙은 경우이거나 둘 다 공중에 뜬 경우이다. 또, 아래 그림의 다섯 번째와 여섯 번째 열처럼 두 덩어리가 꼭지점 하나에서만 만나는 경우도 <strong>주어지지 않는다</strong>.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/cffea63f-b6e6-454a-842d-5d253a7bb71c/-/preview/" style="width: 334px; height: 148px;"></p>

<p>예를 들어, 아래 그림의 예를 보면 8×12 크기의 격자칸이 있다. 이 격자칸의 첫 번째 열에는 6번째 칸부터 8번째 칸까지 막혀 있고, 두 번째 열은 막혀 있는 칸이 없으며, 세 번째 열은 5번째 칸부터 8번째 칸까지 막혀 있다는 것을 알 수 있다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/18e385b6-90e6-48ee-a087-a226bd107eac/-/preview/" style="width: 557px; height: 226px;"></p>

<p>이 격자칸에 위에서 물을 충분히 부었다고 하자. 1번으로 표시된 칸들에는 물이 고임을 쉽게 알 수 있다. 2번으로 표시된 칸들도 마찬가지이다. 3번과 4번으로 표시된 칸들에도 물이 고인다. 이 부분은 주의할 필요가 있다. 이 문제에서는 <strong>공기가 없다</strong>고 생각하기 때문에 4번 부분에도 3번 부분과 같은 높이까지 물이 고인다. (공기는 모든 것을 투과한다고 생각해도 마찬가지이다.) 5번 부분에는 공중에 뜬 덩어리 위에 물이 고인 경우이다. 6번 부분과 같은 경우에는 물이 고이지 않음에 주의하라.</p>

<p>칸들의 배치를 입력으로 받아 물을 충분히 부었을 때 물이 고이는 칸들의 개수를 출력하는 프로그램을 작성하라.</p>

### 입력 

 <p>첫 번째 줄에 두 정수 N, M이 주어진다. (1 ≤ N, M ≤ 100,000). 다음 M개의 줄에는 두 정수 A, B가 주어진다. (1 ≤ A ≤ B ≤ N) 이는 왼쪽에서 오른쪽으로 차례대로, 해당하는 열에 막힌 칸의 위치가 A행부터 B행 까지라는 뜻이다. 특수 한 경우로, 해당하는 열에 막힌 칸이 없으면 A, B 모두 0으로 주어진다.</p>

### 출력 

 <p>주어진 격자에서 물을 충분히 부었을 때 물이 고이는 칸들의 개수를 출력한다.</p>

