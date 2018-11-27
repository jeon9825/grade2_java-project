import java.util.Arrays;
import java.util.Scanner;

public class NumericalBaseball {

	public static void main(String[] args) {
		System.out.print("level을 선택해주세요. 초급(1) 중급(2) 고급(3)  ");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		int n;
		switch (input) {
		case 1:
			n = 3;
			break;
		case 2:
			n = 4;
			break;
		case 3:
			n = 5;
			break;
		default:
			System.out.println("level을 선택하지 않았습니다. 자동적으로 중급으로 실행합니다.");
			n = 4;
		}
		int[] arrRandom = new int[n];
		for (int i = 0; i < arrRandom.length; i++) {
			arrRandom[i] = (int) (Math.random() * 10);
			for (int j = 0; j < i; j++) { // 중복제거
				if (arrRandom[i] == arrRandom[j]) {
					i--;
					break;
				}
			}
		}
		// System.out.println(Arrays.toString(arrRandom));
		int count = 0;
		while (true) {
			count++;
			System.out.println("숫자 " + n + "개를 입력하시오.");
//			@SuppressWarnings("resource")
//			Scanner scan = new Scanner(System.in);
			int num = scan.nextInt();

			int strike = 0;
			int ball = 0;

			int[] arrNum = new int[n];
			for (int i = 0; i < arrNum.length; i++) {
				arrNum[i] = (int) (num / Math.pow(10, n - 1 - i) % 10);
			}

			for (int i = 0; i < arrRandom.length; i++) {
				for (int j = 0; j < arrNum.length; j++) {
					if (i == j && arrRandom[i] == arrNum[j])
						strike++;
					if (i != j && arrRandom[i] == arrNum[j])
						ball++;
				}
			}
			if (strike == n) {
				System.out.println("축하합니다! " + count + "번만에 성공하였습니다.");
				break;
			} else
				System.out.println(strike + "스트라이크 " + ball + "볼\n");
		}
	}

}
