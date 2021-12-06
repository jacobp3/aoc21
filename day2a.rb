$depth = 0
$pos = 0

File.foreach("day2.txt") { |line| 
  direction, distance = line.split();
  if(direction.eql?("forward")) then
    $pos += distance.to_i 
  elsif(direction.eql?("down")) then
    $depth += distance.to_i 
  elsif(direction.eql?("up")) then
    $depth -= distance.to_i 
  end
}

puts $depth.to_s
puts $pos.to_s
puts ($depth * $pos).to_s
