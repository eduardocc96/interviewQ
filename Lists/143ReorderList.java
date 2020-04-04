        //Solution by leetcode's wanqing
        //Amazing runtime better than 99.56% O(N)
        //Bad memory better than only 6% O(1)
        //Amazing solution, three steps
        //1.- Go to half of list with fast and slow pointers
        //2.- Reverse second half
        //3.- Using our middle pointer and start pointer reorder
class Solution {
    public void reorderList(ListNode head) {
        if(head==null||head.next==null) return;

        //Find the middle of the list
        ListNode p1=head;
        ListNode p2=head;
        while(p2.next!=null&&p2.next.next!=null){ 
            p1=p1.next;
            p2=p2.next.next;
        }

        //Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
        ListNode preMiddle=p1;
        ListNode preCurrent=p1.next;
        while(preCurrent.next!=null){
            ListNode current=preCurrent.next;
            preCurrent.next=current.next;
            current.next=preMiddle.next;
            preMiddle.next=current;
        }

        //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
        p1=head;
        p2=preMiddle.next;
        while(p1!=preMiddle){
            preMiddle.next=p2.next;
            p2.next=p1.next;
            p1.next=p2;
            p1=p2.next;
            p2=preMiddle.next;
        }
    }
}
        
        //My solution using HashMap
        //Might not be a great solution but it's not forbidden in problem description
        //And really couldn't think of other way
        //Bad runtime of 6ms better than only 10.93% O(N)
        //Bad memory better than only 6.82% O(N)
        //Save in hashMap and then move links 
class Solution {
    public void reorderList(ListNode head) {
        if(head==null) return ;
        HashMap<Integer,ListNode> nodes=new HashMap<Integer,ListNode>();
        ListNode iter=head;
        int cont=1;
        while(iter!=null){
            nodes.put(cont++,iter);
            iter=iter.next;
        }
        for(int i=1;i<=cont/2;i++){
            nodes.get(i).next=nodes.get(cont-i);
            if(cont-i==i+1) break;
            nodes.get(cont-i).next=nodes.get(i+1);
        }
        nodes.get(((int)Math.ceil(cont/2)+(cont%2==0?0:1))).next=null;
    }
}


class Solution {
    public void reorderList(ListNode head) {
        if(head==null || head.next==null) return;
        //first, find middle
        ListNode slow=head,fast=head,preSlow=null;
        while(fast!=null && fast.next!=null){
            preSlow=slow;
            slow=slow.next;
            fast=fast.next.next;
        }
        //slow is middle, revert all middle and after
        ListNode pre=slow;
        ListNode current=slow.next;
        ListNode next;
        slow.next=null;
        if(current!=null){//
            next=current.next;
            while(next!=null){
                current.next=pre;
                pre=current;
                current=next;
                next=next.next;
            }
            current.next=pre;
            preSlow.next=current;
            
            //one to one
            ListNode head1=head,head2=current;
            System.out.println(head1.val+" "+head2.val);
            while(head1.next!=null && head2.next!=null){
                ListNode next1=head1.next,next2=head2.next;
                if(next1.equals(current)) break;
                head1.next=head2;
                head2.next=next1;
                head1=next1;
                head2=next2;
            }
            head1.next=head2;
        }//else, the list is length one or two
    }
}