<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <header id = "header">
      <div>
      <h1 id="logo">
      <a href="index.jsp">
        <img alt="logoImg" src="../img/logoImg.png" id="logoImg">
      </a>
       </h1>
      </div>
    


    <div id="headerMenu" >
      <ul>
        
        <li>
          <a href="login.jsp">로그인</a>
        </li>
        <li>
          <a href="join.jsp">회원가입</a>
        </li>
        <li>
          <a href="myPage01.jsp">마이페이지</a>
        </li>
        <li>
          <a href="manage01.jsp">관리자페이지</a>
        </li>
      </ul>
    </div>  

  
    <form id="headerSearchForm">
      <legend> 검색 </legend>
       <select id ="searchDiv" name ="searchDiv">
                          <option value="10" selected>제목</option>
                          <option value="20">저자</option>
                          <option value="30">출판사</option>  
       </select>
       <input type= "text" id = "searchInput">
      
      <button type = "submit" ><p> 검색하기 </p></button>
      

    </form>
  </header>

  <nav>
      <ul>
          <li><a href="#">자료검색</a>
            
              <ul id="navSubMenu1">
                  <li><a href="#">서브메뉴1-1</a></li>
                  <li><a href="#">서브메뉴1-2</a></li>
                  <li><a href="#">서브메뉴1-3</a></li>
              </ul>
              
          </li>
          <li><a href="#">베스트셀러</a>
          
              <ul id="navSubMenu2">
              
                  <li><a href="#">서브메뉴2-1</a></li>
                  <li><a href="#">서브메뉴2-2</a></li>
                  <li><a href="#">서브메뉴2-3</a></li>
              </ul>
              
          </li>
          <li><a href="#">커뮤니티</a>
          
              <ul id="navSubMenu3">
                  <li><a href="board01.jsp">공지사항</a></li>
                  <li><a href="board02.jsp">소통마당</a></li>
                
              </ul>
              
          </li>
          <li><a href="#">나만의 도서관</a>
          
              <ul id="navSubMenu4">
                  <li><a href="myPage01.jsp">대출목록</a></li>
                  <li><a href="myPage02.jsp">즐겨찾기</a></li>
               
              </ul>
              
          </li>
      </ul>
  </nav>