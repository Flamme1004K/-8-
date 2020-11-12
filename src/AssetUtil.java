import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class AssetUtil {

    public static int totalAssetValues(final List<Asset> assets, final Predicate<Asset> assetSelector) {
        return assets.stream().filter(assetSelector).mapToInt(Asset::getValue).sum();
    }

    public static int totalBondAssetValues(final List<Asset> assets) {
        return assets.stream().mapToInt(asset -> asset.getType() == Asset.AssetType.BOND ? asset.getValue() : 0).sum();
    }
    public static int totalStockAssetValues(final List<Asset> assets) {
        return assets.stream().mapToInt(asset -> asset.getType() == Asset.AssetType.STOCK ? asset.getValue() : 0).sum();
    }

    public static void main(String[] args) {
        final List<Asset> assets = Arrays.asList(
                new Asset(Asset.AssetType.BOND, 1000),
                new Asset(Asset.AssetType.BOND, 2000),
                new Asset(Asset.AssetType.STOCK, 3000),
                new Asset(Asset.AssetType.STOCK, 4000)
        );

        System.out.println("totalAssetValues(assets) = " + totalAssetValues(assets, asset -> true));
        System.out.println("totalBondAssetValues(assets) = " + totalAssetValues(assets, asset -> asset.getType() == Asset.AssetType.BOND));
        System.out.println("totalStockAssetValues(assets) = " + totalAssetValues(assets, asset -> asset.getType() == Asset.AssetType.STOCK));
    }

}
