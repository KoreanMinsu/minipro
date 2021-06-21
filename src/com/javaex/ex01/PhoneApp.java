package com.javaex.ex01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneApp {
	public static void main(String[] args) throws IOException {
		
		List<Phone> pList = new ArrayList<Phone>();
		//파일읽기용 
		Reader fr = new FileReader("C:\\javastudy\\Workspace\\PhoneDB.txt");
		BufferedReader br = new BufferedReader(fr);
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("************************************");
		System.out.println("*         전화번호 관리 프로그램         *");
		System.out.println("************************************");
		
		
		String text = "";
//프로그램 돌리기용 while문
		boolean flag = true;
		while(flag) { 
			System.out.println("");
			System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
			System.out.println("----------------------------------");
			System.out.print(">메뉴번호: ");
			int num = sc.nextInt();
			
			switch(num) {
				case 1: 
					System.out.println("<1.리스트>");
					while(true) {
						text = br.readLine();
						if(text == null) {
							break;
						}
						String[] pArray = text.split(","); //, 분할 
						String name = pArray[0]; //split -> 매개변수 할당
						String hp = pArray[1];
						String company = pArray[2];
						Phone phone = new Phone(name, hp, company); //pList에 공간생성.
						pList.add(phone); // 입력값저장
					}
					for(int i=0;i<pList.size();i++) {
						System.out.print(i+1+".   "); // 일련번호
						System.out.println(pList.get(i).getName()+"    "+pList.get(i).getHp()+"   "+pList.get(i).getCompany());
					}
					break;
				case 2:	
					System.out.println("<2.등록>");
					sc.nextLine(); // 개행문자 제거용

					System.out.print(">이름: ");
					String newName = sc.nextLine();
					System.out.print(">휴대전화: ");
					String newHp = sc.nextLine();
					System.out.print(">회사전화: ");
					String newCompany = sc.nextLine();
					
					Phone phone = new Phone(newName, newHp, newCompany);
					pList.add(phone);
					
					System.out.println("[등록되었습니다.]");
					break;	//등록기능 구현
				case 3:
					System.out.println("<3.삭제>");
					System.out.print(">번호:");
					int deleteNum = sc.nextInt();
					pList.remove(deleteNum-1);
					
					System.out.println("[삭제되었습니다.]");
					break;
				case 4:
					System.out.println("<4.검색>");
					System.out.print(">이름: ");
					sc.nextLine(); // 개행문자 제거
					String searchName = sc.nextLine();
					//검색기능 구현
					for(int i=0;i<pList.size();i++) {
						if(pList.get(i).getName().contains(searchName)) {
							System.out.print(i+1+".   "); // 일련번호
							System.out.println(pList.get(i).getName()+"    "+pList.get(i).getHp()+"   "+pList.get(i).getCompany());
						}
					}
					break;
				case 5:
					flag=false;
					break; // 리스트관리프로그램 종료
					
				default : 
					System.out.println("[다시 입력해 주세요.]");
					break;
			}
		}
		System.out.println();
		System.out.println("************************************");
		System.out.println("*             감사합니다.             *");
		System.out.println("************************************");
		
		//파일쓰기용
		Writer fw = new FileWriter("C:\\javastudy\\Workspace\\PhoneDB.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(int i=0;i<pList.size();i++) {
			bw.write(pList.get(i).getName()+","+pList.get(i).getHp()+","+pList.get(i).getCompany());
			bw.newLine();
		}
		
		sc.close();
		br.close();
		bw.close();
	}
}
