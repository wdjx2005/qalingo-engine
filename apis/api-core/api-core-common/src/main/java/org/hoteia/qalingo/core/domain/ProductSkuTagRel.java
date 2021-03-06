/**
 * Most of the code in the Qalingo project is copyrighted Hoteia and licensed
 * under the Apache License Version 2.0 (release version 0.8.0)
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *                   Copyright (c) Hoteia, 2012-2014
 * http://www.hoteia.com - http://twitter.com/hoteia - contact@hoteia.com
 *
 */
package org.hoteia.qalingo.core.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hoteia.qalingo.core.domain.impl.DomainEntity;

@Entity
@Table(name = "TECO_PRODUCT_SKU_TAG_REL")
@AssociationOverrides({
    @AssociationOverride(name = "pk.productSku", joinColumns = @JoinColumn(name = "PRODUCT_SKU_ID")),
    @AssociationOverride(name = "pk.tag", joinColumns = @JoinColumn(name = "TAG_ID"))})
public class ProductSkuTagRel extends AbstractEntity<ProductSkuTagRel> implements DomainEntity {

    /**
     * Generated UID
     */
    private static final long serialVersionUID = 2179540918678304872L;

    @EmbeddedId
    private ProductSkuTagPk pk;
    
    @Column(name = "RANKING")
    private Integer ranking;

    @Column(name = "IS_DEFAULT_TAG", nullable = false) // , columnDefinition = "tinyint(1) default 0"
    private boolean isDefaultTag = false;

    public ProductSkuTagRel() {
        this.pk = new ProductSkuTagPk();
    }
    
    public ProductSkuTagRel(final ProductSku productSku, final Tag tag) {
        this.pk = new ProductSkuTagPk(productSku, tag);
    }

    public ProductSkuTagPk getPk() {
        return pk;
    }

    public void setPk(ProductSkuTagPk pk) {
        this.pk = pk;
    }

    @Transient
    public ProductSku getProductSku() {
        return getPk().getProductSku();
    }

    public void setProductSku(final ProductSku productSku) {
        pk.setProductSku(productSku);
    }
    
    @Transient
    public Tag getProductSkuTag() {
        return getPk().getTag();
    }

    public void setProductSkuTagP(final Tag tag) {
        pk.setTag(tag);
    }
    
    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public boolean isDefaultTag() {
        return isDefaultTag;
    }

    public void setDefaultTag(boolean isDefaultTag) {
        this.isDefaultTag = isDefaultTag;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
        result = prime * result + ((ranking == null) ? 0 : ranking.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object sourceObj) {
        Object obj = deproxy(sourceObj);
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductSkuTagRel other = (ProductSkuTagRel) obj;
        if (pk == null) {
            if (other.pk != null)
                return false;
        } else if (!pk.equals(other.pk))
            return false;
        if (ranking == null) {
            if (other.ranking != null)
                return false;
        } else if (!ranking.equals(other.ranking))
            return false;
        return true;
    }

}