<template>
<div id=app >
    <div class="wrapper" >
        <!-- <img src="/question-mark.png" style="position:absolute; left:0%; top:-70%; opacity:0.2; z-index:-10;" /> -->
  <h1 align="center" ><a href="#" style="color:rgba(255, 255, 255, 0.65);" class="effect-underline effect-shine ">거기어디니</a></h1>
  <!-- <h1 align="center"><a href="#" class="effect-box">거기 어디니</a></h1> -->
  <!-- <h1 align="center"><a href="#" class="effect-shine">거기 어디니</a></h1> -->
</div>
</div>
</template>

<style scoped >
@font-face { font-family: '국립박물관문화재단클래식B'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.0/국립박물관문화재단클래식B.woff') format('woff'); font-weight: normal; font-style: normal; }

* {
         font-family: '국립박물관문화재단클래식B';

}
.wrapper {
  display: block;
  position: absolute;
  top: 50%;
  left:50%;
  -webkit-transform: translate(-50%,-50%);
}

h1 a {
  color: white;
  font-size: 4em;
  text-decoration: none;
  display: inline-block;
  position: relative;
}

/*effect-underline*/
a.effect-underline:after {
    content: '';
  position: absolute;
  left: 0;
  display: inline-block;
  height: 180px;
  width: 100%;
  border-bottom: 3px solid;
  border-top: 3px solid;
  margin-top: 7px;
  opacity: 0;
	-webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
	transition: opacity 0.35s, transform 0.35s;
	-webkit-transform: scale(0,1);
    transform: scale(0,1);
    padding:20px;
}

a.effect-underline:hover:after {
  opacity: 1;
	-webkit-transform: scale(1);
    transform: scale(1);
    
}

/* effect-shine */
a.effect-shine:hover {
  -webkit-mask-image: linear-gradient(-105deg, rgba(123, 123, 123, 0.6) 30%, #000 50%, rgba(0,0,0,.6) 70%);
  -webkit-mask-size: 200%;
  animation: shine 2s infinite;
}

@-webkit-keyframes shine {
  from {
    -webkit-mask-position: 150%;
  }
  
  to {
    -webkit-mask-position: -50%;
  }

  
}

/*effect-box*/
a.effect-box:after,
a.effect-box:before {
	content: '';
  position: absolute;
  left: 0;
  display: inline-block;
  height: 1em;
  width: 100%;
  margin-top: 10px;
  opacity: 0;
	-webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
	transition: opacity 0.35s, transform 0.35s;
	
}

a.effect-box:before {
  border-left: 1px solid;
  border-right: 1px solid;
  -webkit-transform: scale(1,0);
	transform: scale(1,0);
}

a.effect-box:after {
  border-bottom: 1px solid;
  border-top: 1px solid;
  -webkit-transform: scale(0,1);
	transform: scale(0,1);
}

a.effect-box:hover:after,
a.effect-box:hover:before {
  opacity: 1;
	-webkit-transform: scale(1);
	transform: scale(1);
}

</style>poI