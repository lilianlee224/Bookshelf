import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.Comparator;

public class Bookshelf {

    private Book[] books;
    private int numComparisions;

    public Bookshelf(Book[] books) {
        this.numComparisions = 0;
        this.books = books;
    }

    public Book[] getBooks() {
        return books;
    }


    public Bookshelf mergeBookshelves(Bookshelf bookshelf, String sortBy){
        Book[] thisBooks = this.books;
        Book[] bookshelfBooks = bookshelf.books;
        Book[] mergedBooks = new Book[thisBooks.length + bookshelfBooks.length];

        int ptrThis = 0;
        int ptrBookshelf = 0;

        while(ptrThis < thisBooks.length && ptrBookshelf < bookshelfBooks.length){
            if(thisBooks[ptrThis].compareTo(bookshelfBooks[ptrBookshelf], sortBy) < 0){
                mergedBooks[ptrBookshelf + ptrThis] = thisBooks[ptrThis];
                ptrThis++;
            }
            else{
                mergedBooks[ptrBookshelf + ptrThis] = bookshelfBooks[ptrBookshelf];
                ptrBookshelf++;
            }
        }

        if(ptrThis < thisBooks.length){
            for(int i = ptrThis; i < thisBooks.length; i++){
                mergedBooks[i + ptrBookshelf] = thisBooks[i];
            }
        }
        else {
            for(int i = ptrBookshelf; i < bookshelfBooks.length; i++){
                mergedBooks[i + ptrThis] = bookshelfBooks[i];
            }
        }

        return new Bookshelf(mergedBooks);
    }

    public Bookshelf mergeSortBookshelf(String sortBy){
        if (books.length > 1){

            System.out.println("books length is " + books.length);

            Book[] books1 = Bookshelf.cloneBookArray(this.books, 0, books.length/2);
            Book[] books2 = Bookshelf.cloneBookArray(this.books, books.length/2, books.length);

            Bookshelf bookshelf1 = new Bookshelf(books1);
            Bookshelf bookshelf2 = new Bookshelf(books2);

            bookshelf1 = bookshelf1.mergeSortBookshelf(sortBy);
            bookshelf2 = bookshelf2.mergeSortBookshelf(sortBy);
            //if(numComparisions > 0){
               // System.out.println("merge sort num comparision: " + numComparisions);
            //}
            return bookshelf1.mergeBookshelves(bookshelf2, sortBy);
        }
        else{
            //numComparisions++;
            return this;
        }
    }

    public static void printBookTitles(Book[] books){
        System.out.println("");
        for (int i = 0; i < books.length; i++){
            System.out.println("title: " + books[i].getTitle());
        }
    }

    public static Book[] cloneBookArray(Book[] books, int startIndex, int endIndex){
        int diff = endIndex - startIndex;
        Book[] returnBooks = new Book[diff];
        for (int i = 0; i < returnBooks.length; i++){
            returnBooks[i] = books[startIndex + i];
        }
        return returnBooks;
    }

    public Bookshelf bubbleSortBookshelf(String sortBy){
        for(int i = books.length; i > 0; i--){
            for(int j = 0; j < i-1; j++){
                if(books[j].compareTo(books[j+1], sortBy) > 0){
                    Book temp = books[j+1];
                    books[j+1] = books[j];
                    books[j] = temp;
                    numComparisions+=1;
                }
            }
        }
        return this;
    }

    public Bookshelf selectionSortBookshelf(String sortBy){
        for(int i=0; i<books.length-1; i++){
            int min = i;
            for(int j=i+1; j<books.length; j++){
                if(books[j].compareTo(books[min], sortBy) < 0){
                    min = j;
                    numComparisions+=1;
                }
            }
            Book temp = books[min];
            books[min] = books[i];
            books[i] = temp;
        }
        return this;
    }

    public static void main(String[] args) {

        Book[] books = {new Book("1984", "Orwell", "Fiction", 528),
                new Book("A Brief History Of Time", "Hawking", "Astronomy", 212),
                new Book("Alice's Adventures in Wonderland", "Carroll", "Fantasy", 272),
                new Book("Harry Potter : The Philosopher's Stone", "Rowling", "Fantasy", 256),
                new Book("Harry Potter : The Chamber of Secrets", "Rowling", "Fantasy", 368),
                new Book("Harry Potter : The Prisoner of Azkaban", "Rowling", "Fantasy", 464),
                new Book("JK Rowling : Autobiography", "Rowling", "Non-Fiction", 500),
                new Book("The Dark Tower: The Gunslinger", "King", "Horror", 224),
                new Book("The Dark Tower: The Drawing of the Three", "King", "Horror", 400),
                new Book("It", "King", "Horror", 1138),
                new Book("Amazing Spider-Man #1", "Ditko", "Comic", 25)};

        Bookshelf bookshelf = new Bookshelf(books);

        Book[] orig;
        orig = cloneBookArray(books,0,books.length);
        //System.out.println("Original");
        //for(int i=0; i<orig.length; i++){
        //    System.out.println(orig[i].getNumPages() + " ");
        //}


        /*//testing compareTo method
        System.out.println(books[0].getAuthor() + " " + books[1].getAuthor());
        System.out.println(books[0].getAuthor().compareTo(books[1].getAuthor()));
        System.out.println(books[3].getTitle() + " " + books[4].getTitle());
        System.out.println(books[3].getTitle().compareTo(books[4].getTitle()));
        System.out.println(books[0].getGenre() + " " + books[1].getGenre());
        System.out.println(books[0].getGenre().compareTo(books[1].getGenre()));
        Integer a = new Integer(books[0].getNumPages());
        Integer b = new Integer(books[1].getNumPages());
        System.out.println("a is " + a + ", b is " + b);
        System.out.println(a.compareTo(b));*/

        //testing selectionSort
        bookshelf = new Bookshelf(books);
        System.out.println("Selection Sorted result");
        bookshelf.selectionSortBookshelf("numPages");
        System.out.println("Selection sort num comparision: " + bookshelf.numComparisions);
        for(int i=0; i<books.length; i++){
            System.out.println(books[i].getNumPages() + " ");
        }

        //reset bookshelve to original
        books = cloneBookArray(orig,0,orig.length);
        //System.out.println("books reset back to original");
        //for(int i=0; i<books.length; i++) {
        //    System.out.println(books[i].getNumPages() + " ");
        //}

        //testing bubbleSort
        bookshelf = new Bookshelf(books);
        System.out.println("BubbleSort Sorted result");
        bookshelf.bubbleSortBookshelf("numPages");
        System.out.println("BubbleSort num comparision: " + bookshelf.numComparisions);
        for(int i=0; i<books.length; i++){
            System.out.println(books[i].getNumPages() + " ");
        }

        //reset bookshelve to original
        books = cloneBookArray(orig,0,orig.length);
        //System.out.println("books reset back to original");
        //for(int i=0; i<books.length; i++) {
        //    System.out.println(books[i].getNumPages() + " ");
        //}

        //testing mergeSort
        bookshelf = new Bookshelf(books);
        System.out.println("MergeSort Sorted result");
        bookshelf.mergeSortBookshelf("numPages");
        System.out.println("MergeSort num comparision: " + bookshelf.numComparisions);
        for(int i=0; i<books.length; i++) {
            System.out.println(books[i].getNumPages() + " ");
        }

    }
}