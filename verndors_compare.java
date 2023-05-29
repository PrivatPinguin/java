import java.util.ArrayList;
import java.util.List;

public class VendorsBox {
    public List<Vendor> equalVendors = new ArrayList<>();
    public int pointer = 0;

    private Vendor fairChoiseVendor(){
        if(pointer>=equalVendors.size()){
            pointer = 0;
        }
        Vendor chosenVendor = equalVendors.get(pointer);
        pointer++;
        return chosenVendor;
    }
    public Vendor findBestVendor(Article article) {
        // scenario 1
        // implement this function
        List<Vendor> vendors= Vendor.getVendorsForArticle(article.getId());

        if( vendors.size() == 0){
            return null;
        }
        else if( vendors.size() == 1){
            return vendors.get(0);
        }
        // get first verndor
        Vendor bestVendor = vendors.get(0);
        for( int i=1; i<vendors.size(); i++){
            ArticleOffer bestAricle = bestVendor.getArticleOffer(article.getId());

            Vendor compareVendor = vendors.get(i);
            ArticleOffer compareArticle = compareVendor.getArticleOffer(article.getId());
            // compare
            boolean setCompareObject = false;

            if(bestAricle.getPrice() > compareArticle.getPrice()){
                setCompareObject = true;
            }else if(bestAricle.getPrice() == compareArticle.getPrice()){
                if(!setCompareObject && (bestAricle.getShippingTime() > compareArticle.getShippingTime())){
                    setCompareObject = true;
                }else if(bestAricle.getShippingTime() == compareArticle.getShippingTime()){
                    if(!setCompareObject && (bestVendor.getRating() < compareVendor.getRating())){
                        setCompareObject = true;
                    }else if(bestVendor.getRating() == compareVendor.getRating()){
                        if(!setCompareObject && (bestAricle.getAvailability() < compareArticle.getAvailability())){
                            setCompareObject = true;
                        }
                    }
                }   
            }
            if(setCompareObject){
                bestVendor = compareVendor;
            }    
        }
        return bestVendor;
    }

    public Vendor findFairBestVendor(Article article) {
        // scenario 2
        // implement this function
        List<Vendor> vendors = Vendor.getVendorsForArticle(article.getId());
        if( vendors.size() == 0){
            return null;
        }
        else if( vendors.size() == 1){
            return vendors.get(0);
        }
        // get first verndor
        Vendor bestVendor = vendors.get(0);
        double lowestPrice = 0;
        if(equalVendors.size() == 0){
            equalVendors.add(bestVendor);
        }
        for( int i=1; i<vendors.size(); i++){
            Vendor compareVendor = vendors.get(i);
            // compare
            lowestPrice = bestVendor.getArticleOffer(article.getId()).getPrice();
            if(lowestPrice == compareVendor.getArticleOffer(article.getId()).getPrice()){
                

                boolean exists = false;
                //get Fair Choice of the Best Vendor
                for(int j=0; j<equalVendors.size(); j++){
                    if(equalVendors.get(j).getId() == compareVendor.getId()){
                        exists = true;
                    }
                }
                if(!exists){
                    equalVendors.add(compareVendor);
                }
                bestVendor = compareVendor;
            } else if(lowestPrice > compareVendor.getArticleOffer(article.getId()).getPrice()){
                equalVendors.clear();
                equalVendors.add(compareVendor);
            }
            bestVendor = compareVendor;
        }
        Vendor chosenVendor = fairChoiseVendor();
        return chosenVendor;
    }
}
