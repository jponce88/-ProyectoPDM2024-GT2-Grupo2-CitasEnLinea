package sv.edu.ues.fia.eisi.proyectoinnovador.classes;

import java.util.List;

public class CryptoData {
    private int id;
    private String name;
    private String symbol;
    private String slug;
    private int num_market_pairs;
    private String date_added;
    private List<String> tags;
    private Long max_supply;
    private double circulating_supply;
    private double total_supply;
    private boolean infinite_supply;
    private Platform platform;
    private int cmc_rank;
    private Double self_reported_circulating_supply;
    private Double self_reported_market_cap;
    private Double tvl_ratio;
    private String last_updated;
    private Quote quote;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getNum_market_pairs() {
        return num_market_pairs;
    }

    public void setNum_market_pairs(int num_market_pairs) {
        this.num_market_pairs = num_market_pairs;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Long getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(Long max_supply) {
        this.max_supply = max_supply;
    }

    public double getCirculating_supply() {
        return circulating_supply;
    }

    public void setCirculating_supply(double circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public double getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(double total_supply) {
        this.total_supply = total_supply;
    }

    public boolean isInfinite_supply() {
        return infinite_supply;
    }

    public void setInfinite_supply(boolean infinite_supply) {
        this.infinite_supply = infinite_supply;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public int getCmc_rank() {
        return cmc_rank;
    }

    public void setCmc_rank(int cmc_rank) {
        this.cmc_rank = cmc_rank;
    }

    public Double getSelf_reported_circulating_supply() {
        return self_reported_circulating_supply;
    }

    public void setSelf_reported_circulating_supply(Double self_reported_circulating_supply) {
        this.self_reported_circulating_supply = self_reported_circulating_supply;
    }

    public Double getSelf_reported_market_cap() {
        return self_reported_market_cap;
    }

    public void setSelf_reported_market_cap(Double self_reported_market_cap) {
        this.self_reported_market_cap = self_reported_market_cap;
    }

    public Double getTvl_ratio() {
        return tvl_ratio;
    }

    public void setTvl_ratio(Double tvl_ratio) {
        this.tvl_ratio = tvl_ratio;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }
}
