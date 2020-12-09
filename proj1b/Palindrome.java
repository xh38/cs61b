public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> returnDeque = new LinkedListDeque<Character>();
        int i = 0;
        while(i < word.length()){
            returnDeque.addLast(word.charAt(i));
            i++;
        }
        return returnDeque;
    }

    public boolean isPalindrome(String word){
        if(word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> inorder = wordToDeque(word);
        String reverse = "";
        int size = inorder.size();
        for(int i = 0;i < size; i++) {
            reverse += inorder.removeLast();
        }
        boolean flag = word.equals(reverse);
        return flag;
    }

    public boolean isPalindrome(String word,CharacterComparator cc) {
        if(word.length() == 0 || word.length() == 1){
            return true;
        }
        if(word.length() % 2 == 0){
            for(int i = 0; i < word.length() / 2; i++) {
                if(!cc.equalChars(word.charAt(i),word.charAt(word.length() - i - 1))){
                    return false;
                }
            }
        }
        else {
            for(int i = 0; i < (word.length() - 1) / 2; i++) {
                if(!cc.equalChars(word.charAt(i),word.charAt(word.length() - i - 1))){
                    return false;
                }
            }
        }
        return true;
    }
}
