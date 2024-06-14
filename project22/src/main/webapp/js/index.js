document.addEventListener('DOMContentLoaded', function () {
    const bannerList = document.querySelector('#sectionBannerDiv ul');
    const banners = bannerList.querySelectorAll('li');
    let currentIndex = 0;

    function moveBanner() {
        const newLeft = -(currentIndex * 100) + '%';
        bannerList.style.left = newLeft;
        currentIndex = (currentIndex + 1) % banners.length;
    }

    setInterval(moveBanner, 3000); // 3초마다 배너 이동
});