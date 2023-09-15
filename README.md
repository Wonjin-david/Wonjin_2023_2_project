# Wonjin_2023_2_project

## practical project 1 (Word CRUD)
영어 단어를 관리하는 CRUD 중 Create와 Read를 구현하였습니다


### 1. Create Word
영어 단어를 추가할 수 있으며, 추가하게 되면 ArrayList에 저장되도록 구현하였습니다.

![스크린샷 2023-09-03 165046](https://github.com/Wonjin-david/Wonjin_2023_2_project/assets/126576242/e689dab9-7301-4b82-8774-579eeac80f8d)

### 2. Read Word
현재까지 입력된 모든 영어단어들을 출력할 수 있습니다. 각 단어들은 ArrayList에 저장되어 사용자가 추후에 관리할 수 있습니다.
![스크린샷 2023-09-03 165121](https://github.com/Wonjin-david/Wonjin_2023_2_project/assets/126576242/61a8fdd9-4c22-4c58-9bee-700f51f2f6d1)

### 3. Update Word
저장된 영어단어 중, 수정을 희망하는 단어를 검색한 후, list를 출력한다. 그 중에서 수정하고픈 단어를 최종선택하고, 수정된 뜻을 입력하면
최종적으로 단어가 수정된다.
![스크린샷 2023-09-14 115804](https://github.com/Wonjin-david/Wonjin_2023_2_project/assets/126576242/bc318c0d-1f3f-4191-9494-b384f0602222)

#### - 수정된 단어 확인을 위한 출력
![스크린샷 2023-09-14 120009](https://github.com/Wonjin-david/Wonjin_2023_2_project/assets/126576242/52766ebf-ff16-4976-b482-be2056772fbc)
=> equipment의 뜻이 "장비, 용품" 에서 "장비"로 수정된 것을 확인할 수 있다.

### 4. Delete Word
저장된 영어단어 중, 삭제를 희망하는 단어를 검색한 후, list를 출력한다. 그 중에서 삭제하고픈 단어를 최종선택하고, 
정말로 삭제할건지 여부를 확인한 다음, 삭제가 진행된다.
![스크린샷 2023-09-14 120309](https://github.com/Wonjin-david/Wonjin_2023_2_project/assets/126576242/1c7d34b4-ef2d-4e30-ae29-e2abf9f2353a)

#### - 삭제된 단어 확인을 위한 출력
![스크린샷 2023-09-14 120433](https://github.com/Wonjin-david/Wonjin_2023_2_project/assets/126576242/86fc87b2-28a9-403b-9b4d-c6150eb7340f)
=> embarrassment가 삭제된 것을 확인할 수 있다.

#### - DB에서도 삭제된 것을 확인
![스크린샷 2023-09-14 120551](https://github.com/Wonjin-david/Wonjin_2023_2_project/assets/126576242/e39a6340-7af0-47c4-8f3a-fa13884fe52b)

### 5. 수준별 단어 확인
저장된 영어단어 중, 출력을 희망하는 레벨을 선택한 후, 해당 레벨의 단어만 출력되도록 한다.
![스크린샷 2023-09-15 215326](https://github.com/Wonjin-david/Wonjin_2023_2_project/assets/126576242/23e3c0a1-79db-46d0-8b0f-06da33a57448)

### 6. 단어 검색
저장된 영어단어 중, 검색하고 싶은 단어를 입력하면, 해당 검색어가 포함된 단어 리스트를 출력하도록 한다.
![스크린샷 2023-09-15 215445](https://github.com/Wonjin-david/Wonjin_2023_2_project/assets/126576242/c57517d1-afa7-4110-a9b1-431f2270cfa0)

### 7. 파일 읽기
프로그램이 시작과 동시에 DB에서 데이터를 읽어와서, Local의 set에 저장하도록 한다.
<br/>
![스크린샷 2023-09-15 215607](https://github.com/Wonjin-david/Wonjin_2023_2_project/assets/126576242/551ff281-5048-4684-a641-970619e38c78)

- DB 파일 목록
![스크린샷 2023-09-15 215729](https://github.com/Wonjin-david/Wonjin_2023_2_project/assets/126576242/b7122e65-3196-40f5-a2a3-ceaa4ab2c154)
=> 위의 실행화면의 나온 것과 동일하게 18개의 데이터가 저장되어 있는 것을 확인할 수 있다.

### 8. 파일 저장
현재 Local set에 저장되어있는 데이터들을 txt 파일에 저장하도록 한다.
<br/>
![스크린샷 2023-09-15 215854](https://github.com/Wonjin-david/Wonjin_2023_2_project/assets/126576242/ba1d827c-8dee-46c8-89d9-b80001987f94)

-txt 파일의 내용
![스크린샷 2023-09-15 220053](https://github.com/Wonjin-david/Wonjin_2023_2_project/assets/126576242/edfb39cb-40c7-4dbe-b7d2-4cfe64e79239)
=> DB와 마찬가지로 18개의 데이터들이 저장되어 있는 것을 확인할 수 있다.
=> 
