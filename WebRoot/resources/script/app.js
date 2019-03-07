function changel()
{
       if(window.top.f2.cols != "0,*")
        {
                window.top.f2.cols = "0,*";
                this.document.show.src="/WeGovPlatform/images/open.gif";
                this.document.show.alt="显示菜单";
        }
        else{
                window.top.f2.cols = "150,*";
                 this.document.show.src="/WeGovPlatform/images/close3.gif";
                 this.document.show.alt="隐藏菜单";
        }


}

