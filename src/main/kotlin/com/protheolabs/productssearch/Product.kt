import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import org.springframework.data.elasticsearch.annotations.CompletionField
import org.springframework.data.elasticsearch.core.completion.Completion

/**
 * Represents a product document in Elasticsearch with support for autocomplete and facets.
 */
@Document(indexName = "products")
data class Product(
    /**
     * The unique identifier of the product.
     */
    @Id
    val id: String,


    /**
     * Product ID from Amazon.
     */
    @Field(type = FieldType.Keyword)
    val asin: String,

    /**
     * Title of the product. (for full-text search)
     */
    @Field(type = FieldType.Text, analyzer = "standard", searchAnalyzer = "standard")
    val title: String,

    /**
     * Url of the product image.
     */
    @Field(type = FieldType.Keyword)
    val imgUrl: String?,

    /**
     * Url of the product.
     */
    @Field(type = FieldType.Keyword)
    val productURL: String?,

    /**
     * Product rating. If null, no ratings were found
     */
    @Field(type = FieldType.Float)
    val stars: Float?,

    /**
     * Number of reviews. If null, no reviews were found.
     */
    @Field(type = FieldType.Integer)
    val reviews: Int?,

    /**
     * Buy now price of the product. If null, price was unavailable. (currency: USD)
     */
    @Field(type = FieldType.Float)
    val price: Float?,

    /**
     * Original price of the product before discount. If null, no list price was found (AKA, no discounts). (currency: USD)
     */
    @Field(type = FieldType.Float)
    val listPrice: Float?,

    /**
     * Actual category name (from amazon_categories.csv).
     */
    @Field(type = FieldType.Keyword)
    val category: String?,

    /**
     * Whether the product had the Amazon BestSeller status or not.
     */
    @Field(type = FieldType.Boolean)
    val isBestSeller: Boolean?,

    /**
     * For autocomplete/suggestion based on title
     */
    @CompletionField
    val suggest: Completion?
)

/**
  * GENERATED
    PUT /products
    {
        "mappings": {
            "properties": {
            "asin": { "type": "keyword" },
            "title": { "type": "text" },
            "imgUrl": { "type": "keyword" },
            "productURL": { "type": "keyword" },
            "stars": { "type": "float" },
            "reviews": { "type": "integer" },
            "price": { "type": "float" },
            "listPrice": { "type": "float" },
            "category_id": { "type": "keyword" },
            "isBestSeller": { "type": "boolean" },
            "suggest": {
                "type": "completion"
            }
        }
    }
}
*/
