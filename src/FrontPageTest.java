import org.w3c.dom.Text;

import java.util.*;
import java.util.stream.Collectors;

class Category{
    String name;

    public Category(String part) {
        name = part;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class NewsItem{
    String title;
    Date date_posted;
    Category category;

    public NewsItem(String title, Date date_posted, Category category) {
        this.title = title;
        this.date_posted = date_posted;
        this.category = category;
    }

}

class TextNewsItem extends NewsItem{

    String textNews;
    public TextNewsItem(String title, Date date_posted, Category category, String textNews) {
        super(title, date_posted, category);
        this.textNews = textNews;
    }

    public String getTeaser(){
        return String.format("%s\n%s\n%s\n", title, 0, OsumdesetZanci());
    }

    public String OsumdesetZanci(){
        StringBuilder result= new StringBuilder();

        for(int i =0; i<textNews.length(); i++){
            if(i<80)
                result.append(textNews.charAt(i));
        }

        return result.toString();
    }

    @Override
    public String toString() {
        return getTeaser();
    }
}

class MediaNewsItem extends NewsItem{
    String url;
    int views;

    public MediaNewsItem(String title, Date date_posted, Category category, String url, int views) {
        super(title, date_posted, category);
        this.url = url;
        this.views = views;
    }


    public String getTeaser(){
        return String.format("%s\n%d\n%s\n%d\n",title, 0, url, views);
    }

    @Override
    public String toString() {
        return getTeaser();
    }
}
class FrontPage{
    List<NewsItem> news;
    Category[] categories;

    public FrontPage(Category[] categories) {
        this.categories = categories;
        news = new ArrayList<>();
    }

    void addNewsItem(NewsItem newsItem){
        news.add(newsItem);
    }

    List<NewsItem> listByCategory(Category category){
        List<NewsItem> result = new ArrayList<>();
        for (NewsItem n:news) {
            if(n.category.equals(category)){
                result.add(n);
            }
        }

        return result;
    }

    List<NewsItem> listByCategoryName(String category) throws CategoryNotFoundException {
        List<NewsItem> result = new ArrayList<>();
        result =news.stream().filter(newsItem ->
                newsItem.category.toString().equalsIgnoreCase(category))
                .collect(Collectors.toList());

        if(result.size()==0){
            throw new CategoryNotFoundException(category);
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (NewsItem newsItem : news) {
            result.append(newsItem.toString());
        }

        return result.toString();
    }
}

class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException(String message) {
        super(String.format("Category %s was not found",message));
    }
}

public class FrontPageTest {
    public static void main(String[] args) {
        // Reading
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        Category[] categories = new Category[parts.length];
        for (int i = 0; i < categories.length; ++i) {
            categories[i] = new Category(parts[i]);
        }
        int n = scanner.nextInt();
        scanner.nextLine();
        FrontPage frontPage = new FrontPage(categories);
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            cal = Calendar.getInstance();
            int min = scanner.nextInt();
            cal.add(Calendar.MINUTE, -min);
            Date date = cal.getTime();
            scanner.nextLine();
            String text = scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            TextNewsItem tni = new TextNewsItem(title, date, categories[categoryIndex], text);
            frontPage.addNewsItem(tni);
        }

        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int min = scanner.nextInt();
            cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, -min);
            scanner.nextLine();
            Date date = cal.getTime();
            String url = scanner.nextLine();
            int views = scanner.nextInt();
            scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            MediaNewsItem mni = new MediaNewsItem(title, date, categories[categoryIndex], url, views);
            frontPage.addNewsItem(mni);
        }
        // Execution
        String category = scanner.nextLine();
        System.out.println(frontPage);
        for(Category c : categories) {
            System.out.println(frontPage.listByCategory(c).size());
        }
        try {
            System.out.println(frontPage.listByCategoryName(category).size());
        } catch(CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}


// Vasiot kod ovde