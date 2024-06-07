<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <header id = "header">
    <h1 id="logo">
      <a>
        <img alt="logoImg" src="logo.png" id="logoImg">
      </a>
    </h1>

    <div id="headerMenu" >
      <ul>
        
        <li>
          <a href="#">로그인</a>
        </li>
        <li>
          <a href="#">회원가입</a>
        </li>
        <li>
          <a href="#">마이페이지</a>
        </li>
        <li>
          <a href="#">관리자페이지</a>
        </li>
      </ul>
    </div>  

  
    <form id="headerSearchForm">
      <legend> 검색 </legend>
       <select id ="searchDiv" name ="searchDiv">
                          <option value="10" selected">제목</option>
                          <option value="20">저자</option>
                          <option value="30">출판사</option>  
       </select>
       <input type= "text">
      
      <button type = "submit"><p> 검색하기 </p></button>

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
                  <li><a href="#">서브메뉴3-1</a></li>
                  <li><a href="#">서브메뉴3-2</a></li>
                  <li><a href="#">서브메뉴3-3</a></li>
              </ul>
              
          </li>
          <li><a href="#">나만의 도서관</a>
          
              <ul id="navSubMenu4">
                  <li><a href="#">서브메뉴4-1</a></li>
                  <li><a href="#">서브메뉴4-2</a></li>
                  <li><a href="#">서브메뉴4-3</a></li>
              </ul>
              
          </li>
      </ul>
  </nav>