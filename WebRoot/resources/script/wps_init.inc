<%
                GlideWindow gw = new GlideWindow("com.wondersgroup.foundation.wps.DataViewerImplforAL");
                gw.setDataSource(arr);
                int pageSize = 20;
                int cur = 1;
                String pos = request.getParameter("pos");
                if(pos!=null && pos.trim().equals("")) pos = null;
                try{
                 	if(pos!=null) cur = Integer.parseInt(pos);
                }catch(Exception e){}
                String gotoPage = request.getParameter("gotoPage");
                if(gotoPage != null && gotoPage.trim().equals("")) gotoPage = null;
                try{
                	if(gotoPage!=null)
                		cur = (Integer.parseInt(gotoPage) - 1) * pageSize + 1;
                }catch(Exception e){}
                while (cur > arr.size()) cur = cur - pageSize;
                if (cur < 1) cur = 1;
                System.out.println("cur-----------------------------------"+cur);

                int pageSpan = 5;
                gw.setSize(pageSize);
                gw.setSpanSize(pageSpan);
                gw.glideTo(cur);
                int curPage = gw.getCurPage();
                ArrayList outList = new ArrayList(gw.getCollection());
                int totalPageNum = gw.getPagesNum();
                int endPage = gw.getEndPage();
                int startPage = gw.getStartPage();
%>
