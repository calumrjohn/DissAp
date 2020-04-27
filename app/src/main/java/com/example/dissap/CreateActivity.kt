package com.example.dissap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.AsyncTask
import android.os.Build
import android.widget.*
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_create_hunt.*
import android.view.*
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Bitmap.CompressFormat
import java.io.ByteArrayOutputStream

private const val TAG = "CreateActivity"
var huntId: Long = 0

class CreateActivity : AppCompatActivity() {

    private var huntDB: HuntDB? = null
    private var imgDB: ImgDB? = null

    val imgs = mutableListOf<Int>(
        R.drawable.library,
        R.drawable.great_hall,
        R.drawable.beach,
        R.drawable.tesco,
        R.drawable.santander,
        R.drawable.tower,
        R.drawable.lounge,
        R.drawable.student_union,
        R.drawable.gym,
        R.drawable.tafarn_tawe,
        R.drawable.food_court,
        R.drawable.bus_stop,
        R.drawable.cofo,
        R.drawable.courts,
        R.drawable.engineering_block
    )
    var title = mutableListOf<String>(
        "Library",
        "Great Hall",
        "Beach",
        "Tesco",
        "Santander",
        "Tower",
        "Lounge",
        "Student Union",
        "Uni Gym",
        "Tafarn Tawe",
        "Food Court",
        "Bus Stop",
        "CoFo",
        "Courts",
        "Engineering Block"
    )
    val hint = mutableListOf<String>(
        "What building has the most stories?",
        "I'm done to boats, to cargo," +
                " to loads. When indoors I'm, in a way, a narrow road. What am I?",
        "I've sand for feet, you " +
                "sometimes sit with me in the heat",
        "Deli meat, to cook or ready to eat, toiletries, paste for" +
                " your teeth and soap for your knees",
        "When in a hurry, come here to withdraw your money",
        "I go way up high into the sky, look at my face and tell the time",
        "This 6 letter word becomes" +
                " long when the 'u' and 'e' are removed",
        "Come here when in need of help, For anything personal " +
                "or mental health",
        "We've got treadmills where you can run, lift so much that you can carry a tonne",
        "Hot chips and cold beers, Pool tables and sports here",
        "Here you have a choice of deal for hot meals, " +
                "chilli con carne to biryani",
        "Wait here to be transported into town, or anywhere around",
        "I only opened 2 years ago, In here computer scientists are taught what they know",
        "Tennis games here " +
                "and fun, Football games and run",
        "Here is where engineers are created, STEM student are also educated"
    )
    val info = mutableListOf<String>(
        "A building containing collections of books, periodicals, films and recorded music " +
                "for use or borrowing by members of the university",
        "Large building used for events, also there's food here",
        "A sandy shore",
        "A local shop on campus to purchase supplies",
        "A bank on campus",
        "The tower, on the bottom" +
                " there is a security office",
        "A common area to chill out",
        "Place where you can speak to comeone or get advice",
        "Place where you can work out membership is £25.99",
        "An cafe with entertainment facilities",
        "Large hall" +
                " with different food vendors to choose from",
        "Designated stop where buses which take you into town will stop",
        "Building where the majority of lectures for CS take place",
        "Hoops and court area to play ball games",
        "The building where STEM students are taught"
    )
    val latitude = mutableListOf<String>(
        "51.617963",
        "51.618476",
        "51.616871",
        "51.618603",
        "51.618529",
        "51.618749",
        "51.618846",
        "51.618986",
        "51.618786",
        "51.618496",
        "51.618433",
        "51.619152",
        "51.619076",
        "51.617547",
        "51.618603"
    )
    val longitude = mutableListOf<String>(
        "-3.877329",
        "-3.878384",
        "-3.877323",
        "-3.879853",
        "-3.880250",
        "-3.879092",
        "-3.879859",
        "-3.881018",
        "-3.881855",
        "-3.880507",
        "-3.881108",
        "-3.879161",
        "-3.878421",
        "-3.879183",
        "-3.877960"
    )

    private val CAMERA_REQUEST = 1888
    private val imageView: ImageView? = null
    private val MY_CAMERA_PERMISSION_CODE = 100
    var arrWaypoint: ArrayList<Waypoint> =  ArrayList()

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_hunt)
        huntDB = HuntDB.getDatabase(this)!!
        imgDB = ImgDB.getDatabase(this)!!
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        val options = BitmapFactory.Options()

        options.inSampleSize = 5
        options.inScaled = true

        val bitMap = mutableListOf<Bitmap>(
            BitmapFactory.decodeResource(resources, R.drawable.library, options),
            BitmapFactory.decodeResource(resources, R.drawable.great_hall, options),
            BitmapFactory.decodeResource(resources, R.drawable.beach, options),
            BitmapFactory.decodeResource(resources, R.drawable.tesco, options),
            BitmapFactory.decodeResource(resources, R.drawable.santander, options),
            BitmapFactory.decodeResource(resources, R.drawable.tower, options),
            BitmapFactory.decodeResource(resources, R.drawable.lounge, options),
            BitmapFactory.decodeResource(resources, R.drawable.student_union, options),
            BitmapFactory.decodeResource(resources, R.drawable.gym, options),
            BitmapFactory.decodeResource(resources, R.drawable.tafarn_tawe, options),
            BitmapFactory.decodeResource(resources, R.drawable.food_court, options),
            BitmapFactory.decodeResource(resources, R.drawable.bus_stop, options),
            BitmapFactory.decodeResource(resources, R.drawable.cofo, options),
            BitmapFactory.decodeResource(resources, R.drawable.courts, options),
            BitmapFactory.decodeResource(resources, R.drawable.engineering_block, options)
        )

        for (i in 0 until imgs.size-1){
            arrWaypoint.add(Waypoint(bitMap[i], title[i], hint[i], info[i], latitude[i], longitude[i]))
        }

        val manager = LinearLayoutManager(this)
        val listView = findViewById<RecyclerView>(R.id.listview)
        listView.layoutManager = LinearLayoutManager(this)

        val customAdaptor = CustomAdaptor(this, arrWaypoint)
        listView.adapter = customAdaptor

        val dividerItemDecoration = DividerItemDecoration(this , manager.orientation)
        listView.addItemDecoration(dividerItemDecoration)

        val callback = DragManageAdapter(customAdaptor, this,
            ItemTouchHelper.UP.or(ItemTouchHelper.DOWN), 0)
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(listView)

        imgInput.setOnClickListener {
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 123)

        }

        val add = findViewById<Button>(R.id.buttonAddWayPoint)
        add.setOnClickListener{
            val huntImageDrawable:BitmapDrawable = imgInput.drawable as BitmapDrawable
            val huntImg:Bitmap = huntImageDrawable.bitmap
            val huntName = editTextName.text.toString()
            val huntHint = editTextHint.text.toString()
            val huntInfo = editTextInfo.text.toString()
            val huntLat = editTextLat.text.toString()
            val huntLong = editTextLong.text.toString()

            arrWaypoint.add(Waypoint(huntImg, huntName, huntHint, huntInfo, huntLat, huntLong))
            customAdaptor.notifyDataSetChanged() 
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==123){
            var bmp = data?.extras?.get("data") as Bitmap
            imgInput.setImageBitmap(bmp)
        }
    }

    class CustomAdaptor(private val context: Activity, var waypoints: ArrayList<Waypoint>
                        ) : RecyclerView.Adapter<CustomAdaptor.WaypointViewHolder>() {

        var mOnWaypointListener: WaypointListener? = null

        fun swapItems(fromPosition: Int, toPosition: Int) {
            if (fromPosition < toPosition) {
                for (i in fromPosition..toPosition - 1) {
                    waypoints.set(i, waypoints.set(i+1, waypoints.get(i)));
                }
            } else {
                for (i in fromPosition..toPosition + 1) {
                    waypoints.set(i, waypoints.set(i-1, waypoints.get(i)));
                }
            }

            notifyItemMoved(fromPosition, toPosition)
        }

        override fun onBindViewHolder(holder: WaypointViewHolder, position: Int) {
            var waypointIndividual: Waypoint = waypoints[position]
            holder.bindData(waypointIndividual.getImage_drawables(), waypointIndividual.getNames(),
                waypointIndividual.getHints(), waypointIndividual.getInfos(), waypointIndividual.getLats(),
                waypointIndividual.getLongs(), mOnWaypointListener)
            holder.delete.setOnClickListener{
                waypoints.removeAt(position)
                notifyDataSetChanged()
            }
        }

        class WaypointViewHolder(itemView: View, internal var mOnWaypointListener: WaypointListener?)
            : RecyclerView.ViewHolder(itemView), View.OnClickListener {

            override fun onClick(v: View?) {
                mOnWaypointListener?.onClicked(adapterPosition)
            }

            var row = itemView.findViewById(R.id.list_item_data) as LinearLayout
            var iconV = itemView.findViewById(R.id.icon) as ImageView
            var titleV = itemView.findViewById(R.id.title) as TextView
            var hintV = itemView.findViewById(R.id.hint) as TextView
            var infoV = itemView.findViewById(R.id.info) as TextView
            var latV = itemView.findViewById(R.id.latitude) as TextView
            var longV = itemView.findViewById(R.id.longitude) as TextView
            var delete = itemView.findViewById(R.id.delete_button) as Button

            fun bindData(
                icon: Bitmap, title: String, hint: String, info: String, lat: String,
                long: String, listener: WaypointListener?
            ) {
                iconV.setImageBitmap(icon)
                titleV.text = title
                hintV.text = hint
                infoV.text = info
                latV.text = lat
                longV.text = long
                row.setOnClickListener { itemView ->
                    listener?.onClicked(position)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaypointViewHolder {
            var layout = LayoutInflater.from(parent.context)
            val view = layout.inflate(R.layout.listview_items, parent, false)
            return WaypointViewHolder(view, mOnWaypointListener)
        }

        override fun getItemCount(): Int {
            return waypoints.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

    }
    fun buttonCreateHunt(view: View) {
        var titleMap: MutableList<String> = arrWaypoint.map { it.name }.toMutableList();
        var hintMap: MutableList<String> = arrWaypoint.map { it.hint }.toMutableList();
        var infoMap: MutableList<String> = arrWaypoint.map { it.info }.toMutableList();
        var latMap: MutableList<String> = arrWaypoint.map { it.lat }.toMutableList();
        var longMap: MutableList<String> = arrWaypoint.map { it.long }.toMutableList();

        InsertHunt(this, createHuntPass(titleMap.joinToString(
            separator = "#",
            postfix = "@"
        ),         hintMap.joinToString(
            separator = "#",
            postfix = "@"
        ),         infoMap.joinToString(
            separator = "#",
            postfix = "@"
        ),         latMap.joinToString(
            separator = "#",
            postfix = "@"
        ),         longMap.joinToString(
            separator = "#",
            postfix = "@"
        ))).execute()

        for (i in 0 until imgs.size-1){
            InsertImg(this, createImgPass(i))
        }
    }

    private fun createImgPass(i: Int): Img {
        return Img(
            huntId,
            bitmapToByte(arrWaypoint[i].image_drawable)
        )
    }

    private fun bitmapToByte(bitmap: Bitmap): ByteArray {
        val blob = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.PNG, 0 /* Ignored for PNGs */, blob)
        val bitmapdata = blob.toByteArray()
        return bitmapdata
    }


    private fun createHuntPass(titles:String, hints: String, infos: String, lats: String, longs: String): Hunt {
        return Hunt(
            editTextHuntName.text.toString(), editTextHuntVictory.text.toString(), titles, hints,
            infos, lats, longs
        )
    }

    private class InsertHunt(var context: CreateActivity, var hunt: Hunt) :
        AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void?): Boolean {
            println(hunt.toString())
            huntId = context.huntDB!!.huntDao().insert(hunt)
            return true
        }

        override fun onPostExecute(bool: Boolean?) {
            if(bool!!) {
                Toast.makeText(context, context.resources.getString(R.string.hunt_created)  , Toast.LENGTH_LONG).show()
            }
        }
    }

    private class InsertImg(var context: CreateActivity, var img: Img) :
        AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void?): Boolean? {
            println(img.toString())
            context.imgDB!!.imgDao().insert(img)
            return true
        }
    }
}