import java.util.Arrays;
import java.util.Scanner;

import java.text.SimpleDateFormat;

import java.util.Date;

public class TPJ_Food {

    static Scanner sc = new Scanner(System.in);

    static Date date = new Date();


    // 사용자의 이름
    static String userName;

    // 사용자의 배열을 조회하기 위한 번호
    static int userInfor = 0;

    // 사용자의 ID, PW, Name의 배열
    static String[][] user = {
            {"id1", "id2", "id3"},
            {"pw1", "pw2", "pw3"},
            {"사장님1", "사장님2", "사장님3"}
    };

    //신규음식점 등록배열
    // idx = user
    static java.lang.String[] market = {" ", " ", " "};

    //메뉴 목록
    static String[][] menu = {
            {"떡볶이", "오뎅", "김말이"},
            {"짜장면", "짬뽕", "탕수육"},
            {"파스타", "피자", "리조또"}
    };
    //메뉴 가격
    static int[][] price = {
            {3000, 2500, 2000},
            {5000, 6000, 10000},
            {10000, 20000, 10000}
    };

    //판매개수 변수선언
    static int sellNum = 0;
    //판매된 메뉴가격변수선언
    static int sellMenuPrice = 0;

    //합산변수
    static int total = 0;

    //판매된 메뉴이름변수
    static String sellMenuName = "";

    //판매, 정산관리에서 추출하기 위해 사용하는 메뉴리스트와 가격리스트 변수
    static String[] sellMenuList = {};
    static int[] sellNumList = {};


    // 고객 조회 idx
    static int idx = 0;


    //재사용 변수
    static String newID = "";
    static String newPW = "";
    static String registration = "";
    static boolean firstRegister = false;

//    ===================================================================================

    //메소드 선언

    static void changeName() {
        System.out.printf("%s 사장님의 상호명 [%s] 입니다.\n", user[2][idx], market[idx]);
        System.out.println("변경하실 상호명을 입력하세요.");
        String[] temp = new String[user.length];
        String marketName = market[idx];
        String changemarketName;
        int changeidx = 0;
        for (int i = 0; i < market.length; i++) {
            if (marketName.equals(market[idx])) {
                changeidx = i;
                break;
            }
        }
        System.out.print(">>");
        changemarketName = sc.next();

        for (int i = changeidx; i < temp.length; i++) {
            market[changeidx] = changemarketName;

        }
        for (int i = 0; i < temp.length; i++) {
            temp[i] = market[i];
        }
        market = temp;
        temp = null;
        System.out.println(Arrays.toString(market));
    }

    // 초기 메세지 메소드
    static void message() {
        System.out.println("원하시는 메뉴를 선택하세요.");
        System.out.println();
        System.out.println("1. 회원가입 진행");
        System.out.println("2. 로그인 진행");
        System.out.println("0. 프로그램 종료");
        System.out.println();
        System.out.print(">> ");
    }


    // 회원가입 메소드
    static void register() {
        int passNum = 0;
        System.out.print(">> ");
        String newID = sc.next();

        for (int i = 0; i < user.length; i++) {
            if (user[0][i].equals(newID)) {
                passNum = 1;
                break;
            }
        }
        if (passNum == 0) {
            System.out.println();
            System.out.println("사용 가능한 아이디입니다.");
            System.out.println("가입을 계속 진행합니다.");
            System.out.println();
        } else {
            System.out.println("중복된 ID 입니다.");
            System.out.println("ID를 다시 입력해주세요.");
        }

        if (passNum == 0) {
            System.out.println("");
            System.out.println("사용하실 비밀번호를 입력해주세요.");
            System.out.print(">> ");
            String newPW = sc.next();
            sc.nextLine();

            System.out.println();
            System.out.printf("사용자 이름: %s 사장님\n", userName);
            System.out.printf("ID: %s\n", newID);
            System.out.printf("PW: %s\n", newPW);
            System.out.println();
            System.out.println("회원가입이 성공적으로 완료되었습니다!");
            firstRegister = true;

            String temp[][] = new String[user.length + 1][user.length + 1];
            for (int i = 0; i < user.length; i++) {
                temp[0][i] = user[0][i];
                temp[1][i] = user[1][i];
                temp[2][i] = user[2][i];
            }

            temp[0][temp.length - 1] = newID;
            temp[1][temp.length - 1] = newPW;
            temp[2][temp.length - 1] = userName;

            user = temp;
            temp = null;

            System.out.println(Arrays.toString(user[0]));
            System.out.println(Arrays.toString(user[1]));
        }
    }

    // menu 2 메세지
    private static void workMenu() {
        System.out.println("처리하실 업무의 번호를 선택하세요.");
        System.out.println("0. 나가기");
        System.out.println("1. 상호명 변경");
        System.out.println("2. 메뉴판 수정");//메뉴 삭제, 가격수정, 메뉴이름 수정
        System.out.println("3. 매출관리");
        System.out.println("4. 정산관리");
        System.out.print(">> ");
    }


    //로그인 메소드
    static void login() {
        // 비밀번호를 통과시키기 위한 번호
        while (true) {
            System.out.print("ID >> ");
            String ID = sc.next();
            int passNum = 0;
            for (int i = 0; i < user.length; i++) {
                if (user[0][i].equals(ID)) {
                    passNum = 1;
                    break;
                } else passNum = 0;
            }
            if (passNum == 1) {
                break;
            } else {
                System.out.println("아이디를 다시 입력해주세요.");
            } // end if
        } // end while 1
        System.out.println("비밀번호를 입력하세요.");
        while (true) {
            // 비밀번호를 통과시키기 위한 번호
            int passPW = 0;
            System.out.print("PW >> ");
            String PW = sc.next();
            for (int i = 0; i < user.length; i++) {
                if (user[1][i].equals(PW)) {
                    passPW = 1;
                    userInfor = i;
                    idx = i;
                    break;
                } else passPW = 0;
            }

            if (passPW == 1) {
                System.out.println();
                System.out.println("로그인에 성공하였습니다.");
                System.out.println(user[2][userInfor] + " 사장님 접속을 환영합니다.");
                break;
            } else {
                System.out.println("비밀번호를 다시 입력해주세요.");
            }
        }
    }


    //로그인 했을 때, 회원가입 유저와 기존 유저의 메세지 출력 메소드
    static void loginMessage() {
        if (user.length == 0) return;
        System.out.println();
        if (firstRegister == true) {
            System.out.println(" 1. 신규 음식점 등록");
            System.out.println(" 2. 가게 관리");
            System.out.print(">> ");
        } else {
            System.out.printf("%s 사장님 접속을 환영합니다.", user[2][userInfor]);
            System.out.println("");
            System.out.println(">> 처리하실 업무를 선택하세요.");
            System.out.println("");
            System.out.println("1. 가게관리");
            System.out.println("### 새로운 서비스 도입 준비 중 입니다.");
            System.out.print(">> ");
        }
    }

    //신규음식점 등록 메소드
    private static void newRegistration() {
        System.out.println("신규 음식점 이름을 입력해주세요.");
        String newFoodMarket = sc.next();

        String[] temp1 = new String[market.length];
        int i = 0;
        for (i = 0; i < temp1.length; i++) {
            temp1[i] = market[i];
        }

        temp1[idx] = newFoodMarket;
        System.out.println(Arrays.toString(temp1));
        market = temp1;
        temp1 = null;
        System.out.println(Arrays.toString(market));
        System.out.printf("%s 사장님의 %s 식당이 개업되었습니다.\n", user[2][idx], market[idx]);
        System.out.printf("사장님의 성공을 항상 기원합니다.\n");

    }

    //매출관리 메소드
    private static void salesManagement(String setTime1) {
        while (true) {
            //해당 점포의 메뉴 띄우기
            String selectUser = user[2][userInfor];
            for (int j = 0; j < user.length; j++) {
                if (selectUser.equals(user[2][j])) {
                    System.out.println("");
                    System.out.printf("%s 사장님 가게에 등록된 메뉴 목록입니다.\n", user[2][userInfor]);
                    System.out.println("업무를 마치시려면 '0' 을 입력해주세요.");
                    System.out.println("");
                    System.out.println(Arrays.toString(menu[userInfor]));
                }//end if
            }//end for

            System.out.println("매출을 입력하실 음식을 입력해주세요.");
            System.out.print(">> ");
            String menuName = sc.next();
            System.out.println("");

            //합산목록
            //입력한 목록 찾기

            //잘못입력했을 때 false로 반환되는 논리타입변수
            boolean corMenuName = false;

            for (int l = 0; l < menu.length; l++) {
                if (menu[userInfor][l].equals(menuName)) {
                    sellMenuName = menuName;
                    System.out.println("판매하신 수량을 입력해주세요.");
                    System.out.print(">> ");
                    sellNum = sc.nextInt();
                    sellMenuPrice = price[userInfor][l];

                    corMenuName = true;

                    //만약 등록한 메뉴를 다시 등록할 경우
                    boolean isAlreadySelled = false;

                    for (int i = 0; i < sellMenuList.length; i++) {
                        if (menuName.equals(sellMenuList[i])) {
                            sellNumList[i] += sellMenuPrice * sellNum;
                            isAlreadySelled = true;
                        }
                    }

                    if (!isAlreadySelled) {
                        //메뉴와 가격을 꽂기 위한 빈배열 생성
                        String temp1[] = new String[sellMenuList.length + 1];
                        int temp2[] = new int[sellNumList.length + 1];

                        //메뉴이름배열과 가격배열에 꽂기
                        for (int i = 0; i < sellNumList.length; i++) {
                            temp1[i] = sellMenuList[i];
                            temp2[i] = sellNumList[i];
                        }
                        temp1[temp1.length - 1] = menuName;

                        temp2[temp2.length - 1] = sellNum * sellMenuPrice;
                        //메뉴, 가격 배열에 빈배열 복사
                        sellMenuList = temp1;
                        sellNumList = temp2;

                        temp1 = null;
                        temp2 = null;
                    }//end inner for

                }//end if
            }//end outer for
            //잘못 입력했을 시
            if (!corMenuName) {
                if (!menuName.equals("0")) {
                    System.out.println("\n다시 입력해주세요");
                    continue;
                }
            }//end if

            //총수익
            total += sellMenuPrice * sellNum;

            //0을 입력했을 시 총 수익과 함께 종료
            if (menuName.equals("0")) {
                corMenuName = true;
                System.out.printf("%s\n", setTime1);
                System.out.printf("현 시간 매출: %d\n", total);
                break;
            }
            System.out.printf("%s %d인분 [%d원]\n", sellMenuName, sellNum, sellNumList[sellNumList.length - 1]);
            System.out.printf("%s\n현 시간 매출: %d\n", setTime1, total);


        }//end while


    }//end method

    public static void main(String[] args) {

        //현재 시간
        SimpleDateFormat timedata = new SimpleDateFormat("y.MM.dd a hh:mm:s");
        String setTime1 = timedata.format(date);


        // 프로그램 실행 초기 화면
        System.out.println(market.length);
        System.out.println();
        System.out.println("ISEC 음식점 관리 프로그램을 실행합니다.");
        System.out.println("점주님들의 번창을 기원합니다.");
        System.out.println();

        // 회원가입
        while (true) {
            //초기 메세지 출력
            message();
            int registerNum = sc.nextInt();
            if (registerNum == 0) break;
            switch (registerNum) {
                case 1:
                    System.out.println();
                    System.out.println("사용자의 성함을 입력해주세요.");
                    System.out.print(">> ");
                    userName = sc.next();
                    System.out.println();
                    System.out.println("사용하실 아이디를 입력해주세요.");
                    register();
                    continue;
                    // end while
                case 2:
                    // 로그인
                    System.out.println();
                    System.out.println("아이디를 입력하세요.");
                    login();
                    break;

                default:
                    System.out.println("메뉴 목차 번호를 정확히 입력해주세요.");
                    break;
            }

            // END LOGIN SYSTEM

            //로그인 이후 메세지, 업무처리


            // 다음 일처리를 하기위한 번호 선택
            String number;

            System.out.println();
            System.out.println(">> 처리하실 업무를 선택하세요.");
            System.out.println();

            while (true) {
                System.out.println(" 1. 신규 음식점 등록");
                System.out.println(" 2. 가게 관리");
                System.out.print(">> ");
                number = sc.next();
                switch (number) {
                    case "1":
                        //신규음식점 등록
                        newRegistration();
                        continue;

                    case "2":
                        workMenu();
                        int rsNumber = sc.nextInt();

                        //가게관리
                        switch (rsNumber) {
                            case 0:
                                System.out.println("업무를 마칩니다.");
                                break;
                            case 1: // 상호명 변경
                                changeName();

                            case 2: //메뉴판 수정
//                                < ========================== >
                            case 3://매출관리
                                salesManagement(setTime1);
                                continue;
                            case 4://정산관리

                                for (int i = 0; i < sellMenuList.length; i++) {

                                    System.out.printf("판매된 메뉴: %s : %d\n", sellMenuList[i], sellNumList[i]);
                                    System.out.println("");
                                }
                                System.out.printf("오늘의 총 수익: %d\n", total);
                                break;

                        }//end switch

                        break;

                    default:
                        System.out.println("다시 입력해주세요.");
                        break;
                }//end switch
            }
        }//end while


    } // end main


}//end class