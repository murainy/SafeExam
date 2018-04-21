
package android.databinding;
import com.murainy.safeexam.BR;
class DataBinderMapperImpl extends android.databinding.DataBinderMapper {
    public DataBinderMapperImpl() {
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case cn.bingoogolapple.baseadapter.R.layout.bga_baseadapter_item_databinding_dummy:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/bga_baseadapter_item_databinding_dummy_0".equals(tag)) {
                            return new cn.bingoogolapple.baseadapter.databinding.BgaBaseadapterItemDatabindingDummyBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for bga_baseadapter_item_databinding_dummy is invalid. Received: " + tag);
                }
        }
        return null;
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    @Override
    public int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case -2069377230: {
                if(tag.equals("layout/bga_baseadapter_item_databinding_dummy_0")) {
                    return cn.bingoogolapple.baseadapter.R.layout.bga_baseadapter_item_databinding_dummy;
                }
                break;
            }
        }
        return 0;
    }
    @Override
    public String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"itemEventHandler"
            ,"model"
            ,"viewHolder"};
    }
}